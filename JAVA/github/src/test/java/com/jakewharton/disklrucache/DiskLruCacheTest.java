/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jakewharton.disklrucache;

import static com.jakewharton.disklrucache.DiskLruCache.JOURNAL_FILE;
import static com.jakewharton.disklrucache.DiskLruCache.JOURNAL_FILE_BACKUP;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class DiskLruCacheTest {

	private final int appVersion = 100;
	private File cacheDir;
	private File journalFile;
	private File journalBkpFile;
	private DiskLruCache cache;

	static final String MAGIC = "libcore.io.DiskLruCache";
	static final String VERSION_1 = "1";

	public static void main(String[] args) throws Exception {
		DiskLruCacheTest test = new DiskLruCacheTest();
		test.setUp();
		test.writeAndReadEntry();
//		test.validateKey();
		test.readAndWriteEntryAcrossCacheOpenAndClose();
		test.tearDown();
	}

	// 初始化
	public void setUp() throws Exception {
		cacheDir = new File("D:\\biao\\DiskLruCacheTest");
		if (!cacheDir.exists()) {
			cacheDir.mkdir();
		}
		journalFile = new File(cacheDir, JOURNAL_FILE);
		journalBkpFile = new File(cacheDir, JOURNAL_FILE_BACKUP);
		for (File file : cacheDir.listFiles()) {
			file.delete();
		}
		cache = DiskLruCache.open(cacheDir, appVersion, 2, Integer.MAX_VALUE);
	}

	// 测试key的命名规则
	public void validateKey() throws Exception {
		String key = null;
		try {
			key = "has_space ";
			cache.edit(key);
		} catch (IllegalArgumentException iae) {
			System.out.println(iae.getMessage());
		}
		try {
			key = "has_CR\r";
			cache.edit(key);
		} catch (IllegalArgumentException iae) {
			System.out.println(iae.getMessage());
		}
		try {
			key = "has_LF\n";
			cache.edit(key);
		} catch (IllegalArgumentException iae) {
			System.out.println(iae.getMessage());
		}
		try {
			key = "has_invalid/";
			cache.edit(key);
		} catch (IllegalArgumentException iae) {
			System.out.println(iae.getMessage());
		}
		try {
			key = "has_invalid\u2603";
			cache.edit(key);
		} catch (IllegalArgumentException iae) {
			System.out.println(iae.getMessage());
		}
		try {
			key = "this_is_way_too_long_this_is_way_too_long_this_is_way_too_long_"
					+ "this_is_way_too_long_this_is_way_too_long_this_is_way_too_long";
			cache.edit(key);
		} catch (IllegalArgumentException iae) {
			System.out.println(iae.getMessage());
		}

		// Test valid cases.

		// Exactly 120.
		key = "0123456789012345678901234567890123456789012345678901234567890123456789"
				+ "01234567890123456789012345678901234567890123456789";
		cache.edit(key).abort();
		// Contains all valid characters.
		key = "abcdefghijklmnopqrstuvwxyz_0123456789";
		cache.edit(key).abort();
		// Contains dash.
		key = "-20384573948576";
		cache.edit(key).abort();
	}

	// 写入 读入
	public void writeAndReadEntry() throws Exception {
		DiskLruCache.Editor creator = cache.edit("k1");
		creator.set(0, "ABC");
		creator.set(1, "DE");
		creator.commit();

		DiskLruCache.Snapshot snapshot = cache.get("k1");
		System.out.println(snapshot.getString(0)); // ;//isEqualTo("ABC");
		System.out.println(snapshot.getLength(0)); // ;//isEqualTo(3);
		System.out.println(snapshot.getString(1)); // ;//isEqualTo("DE");
		System.out.println(snapshot.getLength(1)); // ;//isEqualTo(2);
	}

	public void readAndWriteEntryAcrossCacheOpenAndClose() throws Exception {
		DiskLruCache.Editor creator = cache.edit("k1");
		creator.set(0, "A");
		creator.set(1, "B");
		creator.commit();
		cache.close();

		cache = DiskLruCache.open(cacheDir, appVersion, 2, Integer.MAX_VALUE);
		DiskLruCache.Snapshot snapshot = cache.get("k1");
		System.out.println(snapshot.getString(0));// isEqualTo("A");
		System.out.println(snapshot.getLength(0));// isEqualTo(1);
		System.out.println(snapshot.getString(1));// isEqualTo("B");
		System.out.println(snapshot.getLength(1));// isEqualTo(1);
		snapshot.close();
	}

	public void readAndWriteEntryWithoutProperClose() throws Exception {
		DiskLruCache.Editor creator = cache.edit("k1");
		creator.set(0, "A");
		creator.set(1, "B");
		creator.commit();

		// Simulate a dirty close of 'cache' by opening the cache directory
		// again.
		DiskLruCache cache2 = DiskLruCache.open(cacheDir, appVersion, 2, Integer.MAX_VALUE);
		DiskLruCache.Snapshot snapshot = cache2.get("k1");
		System.out.println(snapshot.getString(0));// isEqualTo("A");
		System.out.println(snapshot.getLength(0));// isEqualTo(1);
		System.out.println(snapshot.getString(1));// isEqualTo("B");
		System.out.println(snapshot.getLength(1));// isEqualTo(1);
		snapshot.close();
		cache2.close();
	}

	public void journalWithEditAndPublish() throws Exception {
		DiskLruCache.Editor creator = cache.edit("k1");
		assertJournalEquals("DIRTY k1"); // DIRTY must always be flushed.
		creator.set(0, "AB");
		creator.set(1, "C");
		creator.commit();
		cache.close();
		assertJournalEquals("DIRTY k1", "CLEAN k1 2 1");
	}

	public void revertedNewFileIsRemoveInJournal() throws Exception {
		DiskLruCache.Editor creator = cache.edit("k1");
		assertJournalEquals("DIRTY k1"); // DIRTY must always be flushed.
		creator.set(0, "AB");
		creator.set(1, "C");
		creator.abort();
		cache.close();
		assertJournalEquals("DIRTY k1", "REMOVE k1");
	}

	public void unterminatedEditIsRevertedOnClose() throws Exception {
		cache.edit("k1");
		cache.close();
		assertJournalEquals("DIRTY k1", "REMOVE k1");
	}

	public void journalDoesNotIncludeReadOfYetUnpublishedValue() throws Exception {
		DiskLruCache.Editor creator = cache.edit("k1");
		System.out.println(cache.get("k1"));// .isNull();
		creator.set(0, "A");
		creator.set(1, "BC");
		creator.commit();
		cache.close();
		assertJournalEquals("DIRTY k1", "CLEAN k1 1 2");
	}

	public void journalWithEditAndPublishAndRead() throws Exception {
	 DiskLruCache.Editor k1Creator = cache.edit("k1");
	 k1Creator.set(0, "AB");
	 k1Creator.set(1, "C");
	 k1Creator.commit();
	 DiskLruCache.Editor k2Creator = cache.edit("k2");
	 k2Creator.set(0, "DEF");
	 k2Creator.set(1, "G");
	 k2Creator.commit();
	 DiskLruCache.Snapshot k1Snapshot = cache.get("k1");
	 k1Snapshot.close();
	 cache.close();
	 assertJournalEquals("DIRTY k1", "CLEAN k1 2 1", "DIRTY k2", "CLEAN k2 3 1", "READ k1");
	 }

	public void cannotOperateOnEditAfterPublish() throws Exception {
		DiskLruCache.Editor editor = cache.edit("k1");
		editor.set(0, "A");
		editor.set(1, "B");
		editor.commit();
		assertInoperable(editor);
	}

	public void cannotOperateOnEditAfterRevert() throws Exception {
		DiskLruCache.Editor editor = cache.edit("k1");
		editor.set(0, "A");
		editor.set(1, "B");
		editor.abort();
		assertInoperable(editor);
	}

	public void explicitRemoveAppliedToDiskImmediately() throws Exception {
		DiskLruCache.Editor editor = cache.edit("k1");
		editor.set(0, "ABC");
		editor.set(1, "B");
		editor.commit();
		File k1 = getCleanFile("k1", 0);
		System.out.println(readFile(k1));// isEqualTo("ABC");
		cache.remove("k1");
		System.out.println(k1.exists());//.isFalse();
	}

	/**
	 * Each read sees a snapshot of the file at the time read was called. This
	 * means that two reads of the same key can see different data.
	 */
	public void readAndWriteOverlapsMaintainConsistency() throws Exception {
		DiskLruCache.Editor v1Creator = cache.edit("k1");
		v1Creator.set(0, "AAaa");
		v1Creator.set(1, "BBbb");
		v1Creator.commit();

		DiskLruCache.Snapshot snapshot1 = cache.get("k1");
		InputStream inV1 = snapshot1.getInputStream(0);
		System.out.println(inV1.read());// isEqualTo('A');
		System.out.println(inV1.read());// isEqualTo('A');

		DiskLruCache.Editor v1Updater = cache.edit("k1");
		v1Updater.set(0, "CCcc");
		v1Updater.set(1, "DDdd");
		v1Updater.commit();

		DiskLruCache.Snapshot snapshot2 = cache.get("k1");
		System.out.println(snapshot2.getString(0));// isEqualTo("CCcc");
		System.out.println(snapshot2.getLength(0));// isEqualTo(4);
		System.out.println(snapshot2.getString(1));// isEqualTo("DDdd");
		System.out.println(snapshot2.getLength(1));// isEqualTo(4);
		snapshot2.close();

		System.out.println(inV1.read());// isEqualTo('a');
		System.out.println(inV1.read());// isEqualTo('a');
		System.out.println(snapshot1.getString(1));// isEqualTo("BBbb");
		System.out.println(snapshot1.getLength(1));// isEqualTo(4);
		snapshot1.close();
	}

	public void openWithDirtyKeyDeletesAllFilesForThatKey() throws Exception {
		cache.close();
		File cleanFile0 = getCleanFile("k1", 0);
		File cleanFile1 = getCleanFile("k1", 1);
		File dirtyFile0 = getDirtyFile("k1", 0);
		File dirtyFile1 = getDirtyFile("k1", 1);
		writeFile(cleanFile0, "A");
		writeFile(cleanFile1, "B");
		writeFile(dirtyFile0, "C");
		writeFile(dirtyFile1, "D");
		createJournal("CLEAN k1 1 1", "DIRTY k1");
		cache = DiskLruCache.open(cacheDir, appVersion, 2, Integer.MAX_VALUE);
		System.out.println(cleanFile0.exists());//.isFalse();
		System.out.println(cleanFile1.exists());//.isFalse();
		System.out.println(dirtyFile0.exists());//.isFalse();
		System.out.println(dirtyFile1.exists());//.isFalse();
		System.out.println(cache.get("k1"));// .isNull();
	}

	public void openWithInvalidVersionClearsDirectory() throws Exception {
		cache.close();
		generateSomeGarbageFiles();
		createJournalWithHeader(MAGIC, "0", "100", "2", "");
		cache = DiskLruCache.open(cacheDir, appVersion, 2, Integer.MAX_VALUE);
		assertGarbageFilesAllDeleted();
	}

	public void openWithInvalidAppVersionClearsDirectory() throws Exception {
		cache.close();
		generateSomeGarbageFiles();
		createJournalWithHeader(MAGIC, "1", "101", "2", "");
		cache = DiskLruCache.open(cacheDir, appVersion, 2, Integer.MAX_VALUE);
		assertGarbageFilesAllDeleted();
	}

	public void openWithInvalidValueCountClearsDirectory() throws Exception {
		cache.close();
		generateSomeGarbageFiles();
		createJournalWithHeader(MAGIC, "1", "100", "1", "");
		cache = DiskLruCache.open(cacheDir, appVersion, 2, Integer.MAX_VALUE);
		assertGarbageFilesAllDeleted();
	}

	public void openWithInvalidBlankLineClearsDirectory() throws Exception {
		cache.close();
		generateSomeGarbageFiles();
		createJournalWithHeader(MAGIC, "1", "100", "2", "x");
		cache = DiskLruCache.open(cacheDir, appVersion, 2, Integer.MAX_VALUE);
		assertGarbageFilesAllDeleted();
	}

	public void openWithInvalidJournalLineClearsDirectory() throws Exception {
		cache.close();
		generateSomeGarbageFiles();
		createJournal("CLEAN k1 1 1", "BOGUS");
		cache = DiskLruCache.open(cacheDir, appVersion, 2, Integer.MAX_VALUE);
		assertGarbageFilesAllDeleted();
		System.out.println(cache.get("k1"));// .isNull();
	}

	public void openWithInvalidFileSizeClearsDirectory() throws Exception {
		cache.close();
		generateSomeGarbageFiles();
		createJournal("CLEAN k1 0000x001 1");
		cache = DiskLruCache.open(cacheDir, appVersion, 2, Integer.MAX_VALUE);
		assertGarbageFilesAllDeleted();
		System.out.println(cache.get("k1"));// .isNull();
	}

	public void openWithTruncatedLineDiscardsThatLine() throws Exception {
	 cache.close();
	 writeFile(getCleanFile("k1", 0), "A");
	 writeFile(getCleanFile("k1", 1), "B");
	 Writer writer = new FileWriter(journalFile);
	 writer.write(MAGIC + "\n" + VERSION_1 + "\n100\n2\n\nCLEAN k1 1 1"); 
	 // no
	 // trailing
	 // newline
	 writer.close();
	 cache = DiskLruCache.open(cacheDir, appVersion, 2, Integer.MAX_VALUE);
	 System.out.println(cache.get("k1"));//.isNull();
	
	 // The journal is not corrupt when editing after a truncated line.
	 set("k1", "C", "D");
	 cache.close();
	 cache = DiskLruCache.open(cacheDir, appVersion, 2, Integer.MAX_VALUE);
	 assertValue("k1", "C", "D");
	 }

	public void openWithTooManyFileSizesClearsDirectory() throws Exception {
		cache.close();
		generateSomeGarbageFiles();
		createJournal("CLEAN k1 1 1 1");
		cache = DiskLruCache.open(cacheDir, appVersion, 2, Integer.MAX_VALUE);
		assertGarbageFilesAllDeleted();
		System.out.println(cache.get("k1"));// .isNull();
	}

	public void keyWithSpaceNotPermitted() throws Exception {
		try {
			cache.edit("my key");

		} catch (IllegalArgumentException expected) {
		}
	}

	public void keyWithNewlineNotPermitted() throws Exception {
		try {
			cache.edit("my\nkey");

		} catch (IllegalArgumentException expected) {
		}
	}

	public void keyWithCarriageReturnNotPermitted() throws Exception {
		try {
			cache.edit("my\rkey");

		} catch (IllegalArgumentException expected) {
		}
	}

	public void nullKeyThrows() throws Exception {
		try {
			cache.edit(null);

		} catch (NullPointerException expected) {
		}
	}

	public void createNewEntryWithTooFewValuesFails() throws Exception {
		DiskLruCache.Editor creator = cache.edit("k1");
		creator.set(1, "A");
		try {
			creator.commit();

		} catch (IllegalStateException expected) {
		}

		System.out.println(getCleanFile("k1", 0).exists());//.isFalse();
		System.out.println(getCleanFile("k1", 1).exists());//.isFalse();
		System.out.println(getDirtyFile("k1", 0).exists());//.isFalse();
		System.out.println(getDirtyFile("k1", 1).exists());//.isFalse();
		System.out.println(cache.get("k1"));// .isNull();

		DiskLruCache.Editor creator2 = cache.edit("k1");
		creator2.set(0, "B");
		creator2.set(1, "C");
		creator2.commit();
	}

	public void revertWithTooFewValues() throws Exception {
		DiskLruCache.Editor creator = cache.edit("k1");
		creator.set(1, "A");
		creator.abort();
		System.out.println(getCleanFile("k1", 0).exists());//.isFalse();
		System.out.println(getCleanFile("k1", 1).exists());//.isFalse();
		System.out.println(getDirtyFile("k1", 0).exists());//.isFalse();
		System.out.println(getDirtyFile("k1", 1).exists());//.isFalse();
		System.out.println(cache.get("k1"));// .isNull();
	}

	public void updateExistingEntryWithTooFewValuesReusesPreviousValues() throws Exception {
		DiskLruCache.Editor creator = cache.edit("k1");
		creator.set(0, "A");
		creator.set(1, "B");
		creator.commit();

		DiskLruCache.Editor updater = cache.edit("k1");
		updater.set(0, "C");
		updater.commit();

		DiskLruCache.Snapshot snapshot = cache.get("k1");
		System.out.println(snapshot.getString(0));// isEqualTo("C");
		System.out.println(snapshot.getLength(0));// isEqualTo(1);
		System.out.println(snapshot.getString(1));// isEqualTo("B");
		System.out.println(snapshot.getLength(1));// isEqualTo(1);
		snapshot.close();
	}

	public void growMaxSize() throws Exception {
		cache.close();
		cache = DiskLruCache.open(cacheDir, appVersion, 2, 10);
		set("a", "a", "aaa"); // size 4
		set("b", "bb", "bbbb"); // size 6
		cache.setMaxSize(20);
		set("c", "c", "c"); // size 12
		System.out.println(cache.size());// isEqualTo(12);
	}

	public void shrinkMaxSizeEvicts() throws Exception {
		cache.close();
		cache = DiskLruCache.open(cacheDir, appVersion, 2, 20);
		set("a", "a", "aaa"); // size 4
		set("b", "bb", "bbbb"); // size 6
		set("c", "c", "c"); // size 12
		cache.setMaxSize(10);
		System.out.println(cache.executorService.getQueue().size());// isEqualTo(1);
		cache.executorService.purge();
	}

	public void evictOnInsert() throws Exception {
		cache.close();
		cache = DiskLruCache.open(cacheDir, appVersion, 2, 10);

		set("a", "a", "aaa"); // size 4
		set("b", "bb", "bbbb"); // size 6
		System.out.println(cache.size());// isEqualTo(10);

		// Cause the size to grow to 12 should evict 'A'.
		set("c", "c", "c");
		cache.flush();
		System.out.println(cache.size());// isEqualTo(8);
		assertAbsent("a");
		assertValue("b", "bb", "bbbb");
		assertValue("c", "c", "c");

		// Causing the size to grow to 10 should evict nothing.
		set("d", "d", "d");
		cache.flush();
		System.out.println(cache.size());// isEqualTo(10);
		assertAbsent("a");
		assertValue("b", "bb", "bbbb");
		assertValue("c", "c", "c");
		assertValue("d", "d", "d");

		// Causing the size to grow to 18 should evict 'B' and 'C'.
		set("e", "eeee", "eeee");
		cache.flush();
		System.out.println(cache.size());// isEqualTo(10);
		assertAbsent("a");
		assertAbsent("b");
		assertAbsent("c");
		assertValue("d", "d", "d");
		assertValue("e", "eeee", "eeee");
	}

	public void evictOnUpdate() throws Exception {
		cache.close();
		cache = DiskLruCache.open(cacheDir, appVersion, 2, 10);

		set("a", "a", "aa"); // size 3
		set("b", "b", "bb"); // size 3
		set("c", "c", "cc"); // size 3
		System.out.println(cache.size());// isEqualTo(9);

		// Causing the size to grow to 11 should evict 'A'.
		set("b", "b", "bbbb");
		cache.flush();
		System.out.println(cache.size());// isEqualTo(8);
		assertAbsent("a");
		assertValue("b", "b", "bbbb");
		assertValue("c", "c", "cc");
	}

	public void evictionHonorsLruFromCurrentSession() throws Exception {
		cache.close();
		cache = DiskLruCache.open(cacheDir, appVersion, 2, 10);
		set("a", "a", "a");
		set("b", "b", "b");
		set("c", "c", "c");
		set("d", "d", "d");
		set("e", "e", "e");
		cache.get("b").close(); // 'B' is now least recently used.

		// Causing the size to grow to 12 should evict 'A'.
		set("f", "f", "f");
		// Causing the size to grow to 12 should evict 'C'.
		set("g", "g", "g");
		cache.flush();
		System.out.println(cache.size());// isEqualTo(10);
		assertAbsent("a");
		assertValue("b", "b", "b");
		assertAbsent("c");
		assertValue("d", "d", "d");
		assertValue("e", "e", "e");
		assertValue("f", "f", "f");
	}

	public void evictionHonorsLruFromPreviousSession() throws Exception {
		set("a", "a", "a");
		set("b", "b", "b");
		set("c", "c", "c");
		set("d", "d", "d");
		set("e", "e", "e");
		set("f", "f", "f");
		cache.get("b").close(); // 'B' is now least recently used.
		System.out.println(cache.size());// isEqualTo(12);
		cache.close();
		cache = DiskLruCache.open(cacheDir, appVersion, 2, 10);

		set("g", "g", "g");
		cache.flush();
		System.out.println(cache.size());// isEqualTo(10);
		assertAbsent("a");
		assertValue("b", "b", "b");
		assertAbsent("c");
		assertValue("d", "d", "d");
		assertValue("e", "e", "e");
		assertValue("f", "f", "f");
		assertValue("g", "g", "g");
	}

	public void cacheSingleEntryOfSizeGreaterThanMaxSize() throws Exception {
		cache.close();
		cache = DiskLruCache.open(cacheDir, appVersion, 2, 10);
		set("a", "aaaaa", "aaaaaa"); // size=11
		cache.flush();
		assertAbsent("a");
	}

	public void cacheSingleValueOfSizeGreaterThanMaxSize() throws Exception {
		cache.close();
		cache = DiskLruCache.open(cacheDir, appVersion, 2, 10);
		set("a", "aaaaaaaaaaa", "a"); // size=12
		cache.flush();
		assertAbsent("a");
	}

	public void constructorDoesNotAllowZeroCacheSize() throws Exception {
		try {
			DiskLruCache.open(cacheDir, appVersion, 2, 0);

		} catch (IllegalArgumentException expected) {
		}
	}

	public void constructorDoesNotAllowZeroValuesPerEntry() throws Exception {
		try {
			DiskLruCache.open(cacheDir, appVersion, 0, 10);

		} catch (IllegalArgumentException expected) {
		}
	}

	public void removeAbsentElement() throws Exception {
		cache.remove("a");
	}

	public void readingTheSameStreamMultipleTimes() throws Exception {
		set("a", "a", "b");
		DiskLruCache.Snapshot snapshot = cache.get("a");
		System.out.println(snapshot.getInputStream(0));//.isSameAs(snapshot.getInputStream(0));
		snapshot.close();
	}

	public void rebuildJournalOnRepeatedReads() throws Exception {
		set("a", "a", "a");
		set("b", "b", "b");
		long lastJournalLength = 0;
		while (true) {
			long journalLength = journalFile.length();
			assertValue("a", "a", "a");
			assertValue("b", "b", "b");
			if (journalLength < lastJournalLength) {
				System.out.printf("Journal compacted from %s bytes to %s bytes\n", lastJournalLength, journalLength);
				break; // Test passed!
			}
			lastJournalLength = journalLength;
		}
	}

	public void rebuildJournalOnRepeatedEdits() throws Exception {
		long lastJournalLength = 0;
		while (true) {
			long journalLength = journalFile.length();
			set("a", "a", "a");
			set("b", "b", "b");
			if (journalLength < lastJournalLength) {
				System.out.printf("Journal compacted from %s bytes to %s bytes\n", lastJournalLength, journalLength);
				break;
			}
			lastJournalLength = journalLength;
		}

		// Sanity check that a rebuilt journal behaves normally.
		assertValue("a", "a", "a");
		assertValue("b", "b", "b");
	}

	/**
	 * @see <a href="https://github.com/JakeWharton/DiskLruCache/issues/28">
	 *      Issue #28</a>
	 */

	public void rebuildJournalOnRepeatedReadsWithOpenAndClose() throws Exception {
		set("a", "a", "a");
		set("b", "b", "b");
		long lastJournalLength = 0;
		while (true) {
			long journalLength = journalFile.length();
			assertValue("a", "a", "a");
			assertValue("b", "b", "b");
			cache.close();
			cache = DiskLruCache.open(cacheDir, appVersion, 2, Integer.MAX_VALUE);
			if (journalLength < lastJournalLength) {
				System.out.printf("Journal compacted from %s bytes to %s bytes\n", lastJournalLength, journalLength);
				break; // Test passed!
			}
			lastJournalLength = journalLength;
		}
	}

	/**
	 * @see <a href="https://github.com/JakeWharton/DiskLruCache/issues/28">
	 *      Issue #28</a>
	 */

	public void rebuildJournalOnRepeatedEditsWithOpenAndClose() throws Exception {
		long lastJournalLength = 0;
		while (true) {
			long journalLength = journalFile.length();
			set("a", "a", "a");
			set("b", "b", "b");
			cache.close();
			cache = DiskLruCache.open(cacheDir, appVersion, 2, Integer.MAX_VALUE);
			if (journalLength < lastJournalLength) {
				System.out.printf("Journal compacted from %s bytes to %s bytes\n", lastJournalLength, journalLength);
				break;
			}
			lastJournalLength = journalLength;
		}
	}

	public void restoreBackupFile() throws Exception {
		DiskLruCache.Editor creator = cache.edit("k1");
		creator.set(0, "ABC");
		creator.set(1, "DE");
		creator.commit();
		cache.close();

		System.out.println(journalFile.renameTo(journalBkpFile));//.isTrue();
		System.out.println(journalFile.exists());//.isFalse();

		cache = DiskLruCache.open(cacheDir, appVersion, 2, Integer.MAX_VALUE);

		DiskLruCache.Snapshot snapshot = cache.get("k1");
		System.out.println(snapshot.getString(0));// isEqualTo("ABC");
		System.out.println(snapshot.getLength(0));// isEqualTo(3);
		System.out.println(snapshot.getString(1));// isEqualTo("DE");
		System.out.println(snapshot.getLength(1));// isEqualTo(2);

		System.out.println(journalBkpFile.exists());//.isFalse();
		System.out.println(journalFile.exists());//.isTrue();
	}

	public void journalFileIsPreferredOverBackupFile() throws Exception {
		DiskLruCache.Editor creator = cache.edit("k1");
		creator.set(0, "ABC");
		creator.set(1, "DE");
		creator.commit();
		cache.flush();

		//FileUtils.copyFile(journalFile, journalBkpFile);

		creator = cache.edit("k2");
		creator.set(0, "F");
		creator.set(1, "GH");
		creator.commit();
		cache.close();

		System.out.println(journalFile.exists());//.isTrue();
		System.out.println(journalBkpFile.exists());//.isTrue();

		cache = DiskLruCache.open(cacheDir, appVersion, 2, Integer.MAX_VALUE);

		DiskLruCache.Snapshot snapshotA = cache.get("k1");
		System.out.println(snapshotA.getString(0));// isEqualTo("ABC");
		System.out.println(snapshotA.getLength(0));// isEqualTo(3);
		System.out.println(snapshotA.getString(1));// isEqualTo("DE");
		System.out.println(snapshotA.getLength(1));// isEqualTo(2);

		DiskLruCache.Snapshot snapshotB = cache.get("k2");
		System.out.println(snapshotB.getString(0));// isEqualTo("F");
		System.out.println(snapshotB.getLength(0));// isEqualTo(1);
		System.out.println(snapshotB.getString(1));// isEqualTo("GH");
		System.out.println(snapshotB.getLength(1));// isEqualTo(2);

		System.out.println(journalBkpFile.exists());//.isFalse();
		System.out.println(journalFile.exists());//.isTrue();
	}

	public void openCreatesDirectoryIfNecessary() throws Exception {
		cache.close();
		File dir =new File("D:\\biao\\testOpenCreatesDirectoryIfNecessary");
		cache = DiskLruCache.open(dir, appVersion, 2, Integer.MAX_VALUE);
		set("a", "a", "a");
		System.out.println(new File(dir, "a.0").exists());//.isTrue();
		System.out.println(new File(dir, "a.1").exists());//.isTrue();
		System.out.println(new File(dir, "journal").exists());//.isTrue();
	}

	public void fileDeletedExternally() throws Exception {
		set("a", "a", "a");
		getCleanFile("a", 1).delete();
		System.out.println(cache.get("a"));// .isNull();
	}

	public void editSameVersion() throws Exception {
		set("a", "a", "a");
		DiskLruCache.Snapshot snapshot = cache.get("a");
		DiskLruCache.Editor editor = snapshot.edit();
		editor.set(1, "a2");
		editor.commit();
		assertValue("a", "a", "a2");
	}

	public void editSnapshotAfterChangeAborted() throws Exception {
		set("a", "a", "a");
		DiskLruCache.Snapshot snapshot = cache.get("a");
		DiskLruCache.Editor toAbort = snapshot.edit();
		toAbort.set(0, "b");
		toAbort.abort();
		DiskLruCache.Editor editor = snapshot.edit();
		editor.set(1, "a2");
		editor.commit();
		assertValue("a", "a", "a2");
	}

	public void editSnapshotAfterChangeCommitted() throws Exception {
		set("a", "a", "a");
		DiskLruCache.Snapshot snapshot = cache.get("a");
		DiskLruCache.Editor toAbort = snapshot.edit();
		toAbort.set(0, "b");
		toAbort.commit();
		System.out.println(snapshot.edit());// .isNull();
	}

	public void editSinceEvicted() throws Exception {
		cache.close();
		cache = DiskLruCache.open(cacheDir, appVersion, 2, 10);
		set("a", "aa", "aaa"); // size 5
		DiskLruCache.Snapshot snapshot = cache.get("a");
		set("b", "bb", "bbb"); // size 5
		set("c", "cc", "ccc"); // size 5; will evict 'A'
		cache.flush();
		System.out.println(snapshot.edit());// .isNull();
	}

	public void editSinceEvictedAndRecreated() throws Exception {
		cache.close();
		cache = DiskLruCache.open(cacheDir, appVersion, 2, 10);
		set("a", "aa", "aaa"); // size 5
		DiskLruCache.Snapshot snapshot = cache.get("a");
		set("b", "bb", "bbb"); // size 5
		set("c", "cc", "ccc"); // size 5; will evict 'A'
		set("a", "a", "aaaa"); // size 5; will evict 'B'
		cache.flush();
		System.out.println(snapshot.edit());// .isNull();
	}

	/**
	 * @see <a href="https://github.com/JakeWharton/DiskLruCache/issues/2">Issue
	 *      #2</a>
	 */

	public void aggressiveClearingHandlesWrite() throws Exception {
		FileUtils.deleteDirectory(cacheDir);
		set("a", "a", "a");
		assertValue("a", "a", "a");
	}

	/**
	 * @see <a href="https://github.com/JakeWharton/DiskLruCache/issues/2">Issue
	 *      #2</a>
	 */

	public void aggressiveClearingHandlesEdit() throws Exception {
		set("a", "a", "a");
		DiskLruCache.Editor a = cache.get("a").edit();
		FileUtils.deleteDirectory(cacheDir);
		a.set(1, "a2");
		a.commit();
	}

	public void removeHandlesMissingFile() throws Exception {
		set("a", "a", "a");
		getCleanFile("a", 0).delete();
		cache.remove("a");
	}

	/**
	 * @see <a href="https://github.com/JakeWharton/DiskLruCache/issues/2">Issue
	 *      #2</a>
	 */

	public void aggressiveClearingHandlesPartialEdit() throws Exception {
		set("a", "a", "a");
		set("b", "b", "b");
		DiskLruCache.Editor a = cache.get("a").edit();
		a.set(0, "a1");
		FileUtils.deleteDirectory(cacheDir);
		a.set(1, "a2");
		a.commit();
		System.out.println(cache.get("a"));// .isNull();
	}

	/**
	 * @see <a href="https://github.com/JakeWharton/DiskLruCache/issues/2">Issue
	 *      #2</a>
	 */

	public void aggressiveClearingHandlesRead() throws Exception {
		FileUtils.deleteDirectory(cacheDir);
		System.out.println(cache.get("a"));// .isNull();
	}

	private void assertJournalEquals(String... expectedBodyLines) throws Exception {
		List<String> expectedLines = new ArrayList<String>();
		expectedLines.add(MAGIC);
		expectedLines.add(VERSION_1);
		expectedLines.add("100");
		expectedLines.add("2");
		expectedLines.add("");
		expectedLines.addAll(Arrays.asList(expectedBodyLines));
		System.out.println(readJournalLines());// isEqualTo(expectedLines);
	}

	private void createJournal(String... bodyLines) throws Exception {
		createJournalWithHeader(MAGIC, VERSION_1, "100", "2", "", bodyLines);
	}

	private void createJournalWithHeader(String magic, String version, String appVersion, String valueCount,
			String blank, String... bodyLines) throws Exception {
		Writer writer = new FileWriter(journalFile);
		writer.write(magic + "\n");
		writer.write(version + "\n");
		writer.write(appVersion + "\n");
		writer.write(valueCount + "\n");
		writer.write(blank + "\n");
		for (String line : bodyLines) {
			writer.write(line);
			writer.write('\n');
		}
		writer.close();
	}

	private List<String> readJournalLines() throws Exception {
		List<String> result = new ArrayList<String>();
		BufferedReader reader = new BufferedReader(new FileReader(journalFile));
		String line;
		while ((line = reader.readLine()) != null) {
			result.add(line);
		}
		reader.close();
		return result;
	}

	private File getCleanFile(String key, int index) {
		return new File(cacheDir, key + "." + index);
	}

	private File getDirtyFile(String key, int index) {
		return new File(cacheDir, key + "." + index + ".tmp");
	}

	private static String readFile(File file) throws Exception {
		Reader reader = new FileReader(file);
		StringWriter writer = new StringWriter();
		char[] buffer = new char[1024];
		int count;
		while ((count = reader.read(buffer)) != -1) {
			writer.write(buffer, 0, count);
		}
		reader.close();
		return writer.toString();
	}

	public static void writeFile(File file, String content) throws Exception {
		FileWriter writer = new FileWriter(file);
		writer.write(content);
		writer.close();
	}

	private static void assertInoperable(DiskLruCache.Editor editor) throws Exception {
		try {
			editor.getString(0);

		} catch (IllegalStateException expected) {
		}
		try {
			editor.set(0, "A");

		} catch (IllegalStateException expected) {
		}
		try {
			editor.newInputStream(0);

		} catch (IllegalStateException expected) {
		}
		try {
			editor.newOutputStream(0);

		} catch (IllegalStateException expected) {
		}
		try {
			editor.commit();

		} catch (IllegalStateException expected) {
		}
		try {
			editor.abort();

		} catch (IllegalStateException expected) {
		}
	}

	private void generateSomeGarbageFiles() throws Exception {
		File dir1 = new File(cacheDir, "dir1");
		File dir2 = new File(dir1, "dir2");
		writeFile(getCleanFile("g1", 0), "A");
		writeFile(getCleanFile("g1", 1), "B");
		writeFile(getCleanFile("g2", 0), "C");
		writeFile(getCleanFile("g2", 1), "D");
		writeFile(getCleanFile("g2", 1), "D");
		writeFile(new File(cacheDir, "otherFile0"), "E");
		dir1.mkdir();
		dir2.mkdir();
		writeFile(new File(dir2, "otherFile1"), "F");
	}

	private void assertGarbageFilesAllDeleted() throws Exception {
		System.out.println(getCleanFile("g1", 0));//.doesNotExist();
		System.out.println(getCleanFile("g1", 1));//.doesNotExist();
		System.out.println(getCleanFile("g2", 0));//.doesNotExist();
		System.out.println(getCleanFile("g2", 1));//.doesNotExist();
		System.out.println(new File(cacheDir, "otherFile0"));//.doesNotExist();
		System.out.println(new File(cacheDir, "dir1"));//.doesNotExist();
	}

	private void set(String key, String value0, String value1) throws Exception {
		DiskLruCache.Editor editor = cache.edit(key);
		editor.set(0, value0);
		editor.set(1, value1);
		editor.commit();
	}

	private void assertAbsent(String key) throws Exception {
		DiskLruCache.Snapshot snapshot = cache.get(key);
		if (snapshot != null) {
			snapshot.close();

		}
		System.out.println(getCleanFile(key, 0));//.doesNotExist();
		System.out.println(getCleanFile(key, 1));//.doesNotExist();
		System.out.println(getDirtyFile(key, 0));//.doesNotExist();
		System.out.println(getDirtyFile(key, 1));//.doesNotExist();
	}

	private void assertValue(String key, String value0, String value1) throws Exception {
		DiskLruCache.Snapshot snapshot = cache.get(key);
		System.out.println(snapshot.getString(0));// isEqualTo(value0);
		System.out.println(snapshot.getLength(0));// isEqualTo(value0.length());
		System.out.println(snapshot.getString(1));// isEqualTo(value1);
		System.out.println(snapshot.getLength(1));// isEqualTo(value1.length());
		System.out.println(getCleanFile(key, 0));//.exists();
		System.out.println(getCleanFile(key, 1));//.exists();
		snapshot.close();
	}

	// 退出
	public void tearDown() throws Exception {
		cache.close();
	}
}

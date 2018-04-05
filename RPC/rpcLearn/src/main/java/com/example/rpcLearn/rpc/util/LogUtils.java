/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.example.rpcLearn.rpc.util;

import java.text.MessageFormat;

import org.apache.log4j.Logger;

/**
 *
 * @author zhangwei_david
 * @version $Id: LogUtils.java, v 0.1 2014��11��27�� ����11:41:22 zhangwei_david Exp $
 */
public class LogUtils {

    public static final void info(Logger logger, String pattern, Object... args) {
        if (logger.isInfoEnabled()) {
            logger.info(MessageFormat.format(pattern, args));
        }
    }

    public static final void debug(Logger logger, String pattern, Object... args) {
        if (logger.isDebugEnabled()) {
            logger.debug(MessageFormat.format(pattern, args));
        }
    }

    public static final void error(Logger logger, String pattern, Object... args) {
        logger.error(MessageFormat.format(pattern, args));
    }

    public static final void error(Logger logger, String pattern, Throwable throwable,
                                   Object... args) {
        logger.error(MessageFormat.format(pattern, args), throwable);
    }

    public static final void warn(Logger logger, String pattern, Object... args) {
        logger.warn(MessageFormat.format(pattern, args));
    }
}

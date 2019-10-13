package com.jdjr.knowledge.test.frame.internal.util;

import com.jdjr.knowledge.test.frame.internal.config.Init.Save;
import com.jdjr.knowledge.test.frame.internal.response.ResultContent;

public class SaveUtil {
    public SaveUtil() {
    }

    public static void save(Save save, ResultContent resultContent) {
        switch(save) {
            case SINGLE:
                signal(resultContent);
                break;
            case SCENE:
                scene(resultContent);
        }

    }

    private static void signal(ResultContent resultContent) {
        if (ThreadLocalUtil.getTestSaveFlag()) {
            ThreadLocalUtil.setSignalResultContentList(resultContent);
        }

    }

    private static void scene(ResultContent resultContent) {
        if (ThreadLocalUtil.getTestSaveFlag()) {
            ThreadLocalUtil.setSceneResultContentList(resultContent);
        }

    }
}

package com.ai.reviewer.sdk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ai.reviewer.sdk.domain.service.impl.OpenAiCodeReviewService;
import com.ai.reviewer.sdk.infrastructure.git.GitCommand;
import com.ai.reviewer.sdk.infrastructure.openai.IOpenAI;
import com.ai.reviewer.sdk.infrastructure.openai.impl.ChatGLM;
import com.ai.reviewer.sdk.infrastructure.weixin.WeiXin;

public class OpenAiCodeReview {

    private static final Logger logger = LoggerFactory.getLogger(OpenAiCodeReview.class);

    public static void main(String[] args) throws Exception {
        GitCommand gitCommand = new GitCommand(
                getEnv("GITHUB_REVIEW_LOG_URI"),
                getEnv("GITHUB_TOKEN"),
                getEnv("COMMIT_PROJECT"),
                getEnv("COMMIT_BRANCH"),
                getEnv("COMMIT_AUTHOR"),
                getEnv("COMMIT_MESSAGE")
        );

        WeiXin weiXin = new WeiXin(
                getEnv("WEIXIN_APPID"),
                getEnv("WEIXIN_SECRET"),
                getEnv("WEIXIN_TOUSER"),
                getEnv("WEIXIN_TEMPLATE_ID")
        );

        IOpenAI openAI = new ChatGLM(getEnv("CHATGLM_APIHOST"), getEnv("CHATGLM_APIKEYSECRET"));

        OpenAiCodeReviewService openAiCodeReviewService = new OpenAiCodeReviewService(gitCommand, openAI, weiXin);
        openAiCodeReviewService.exec();

        logger.info("openai-code-review done!");
    }

    private static String getEnv(String key) {
        String value = System.getenv(key);
        if (null == value || value.isEmpty()) {
            throw new RuntimeException("Environment variable " + key + " is null or empty");
        }
        return value;
    }

}

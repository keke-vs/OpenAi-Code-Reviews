package com.ai.reviewer.sdk.infrastructure.openai;

import com.ai.reviewer.sdk.infrastructure.openai.dto.ChatCompletionRequestDTO;
import com.ai.reviewer.sdk.infrastructure.openai.dto.ChatCompletionSyncResponseDTO;

public interface IOpenAI {

    ChatCompletionSyncResponseDTO completions(ChatCompletionRequestDTO requestDTO) throws Exception;

}

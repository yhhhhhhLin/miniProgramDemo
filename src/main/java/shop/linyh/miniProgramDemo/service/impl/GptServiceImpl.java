package shop.linyh.miniProgramDemo.service.impl;

import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author linzz
 */
@Service
public class GptServiceImpl {

    @Autowired
    OpenAiChatModel openAiChatModel;

    public void test() {

        UserMessage userMessage = new UserMessage("this user question");
        SystemMessage systemMessage = new SystemMessage("this system message");
        OpenAiChatOptions chatOptions = OpenAiChatOptions.builder()
                .withResponseFormat(new OpenAiApi.ChatCompletionRequest.ResponseFormat("json_object"))
                .build();
        Prompt prompt = new Prompt(List.of(systemMessage, userMessage));

        ChatResponse response = openAiChatModel.call(prompt);

    }
}

package shop.linyh.miniProgramDemo.service.impl;

import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.ModelOptionsUtils;
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
        try {
            UserMessage userMessage = new UserMessage("this user question");
            SystemMessage systemMessage = new SystemMessage("this system message");

            OpenAiApi.FunctionTool functionTools = new OpenAiApi.FunctionTool(
                    OpenAiApi.FunctionTool.Type.FUNCTION,
                    new OpenAiApi.FunctionTool.Function("Get the weather in location. Return temperature in Celsius.",
                            "getCurrentWeather", ModelOptionsUtils.jsonToMap("""
                            {
                            	"type": "object",
                            	"properties": {
                            		"location": {
                            			"type": "string",
                            			"description": "The city and state e.g. San Francisco, CA"
                            		},
                            		"lat": {
                            			"type": "number",
                            			"description": "The city latitude"
                            		},
                            		"lon": {
                            			"type": "number",
                            			"description": "The city longitude"
                            		},
                            		"unit": {
                            			"type": "string",
                            			"enum": ["C", "F"]
                            		}
                            	},
                            	"required": ["location", "lat", "lon", "unit"]
                            }
                            """)));

            OpenAiChatOptions chatOptions = OpenAiChatOptions.builder()
                    .withTools(List.of(functionTools))
                    .build();

            Prompt prompt = new Prompt(List.of(systemMessage, userMessage), chatOptions);

            openAiChatModel.call(prompt);
        } catch (Exception e) {
            System.err.println("Error occurred during OpenAI API call: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void test2() {
        String hello = openAiChatModel.call("hello");
        openAiChatModel.stream("ab");
        System.out.println(hello);
    }
}

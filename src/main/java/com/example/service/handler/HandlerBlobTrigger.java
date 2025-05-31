package com.example.service.handler;

import com.example.service.model.dto.ReceivedMessageDto;
import com.example.service.service.ServiceFunctionExample;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class HandlerBlobTrigger{

    private final ServiceFunctionExample serviceFunctionExample;

    @FunctionName("handleBlobTrigger")
    public void run(
            @BlobTrigger(
                    name = "content",
                    path = "container-training/{name}.json",
                    dataType = "binary",
                    connection = "AzureWebJobsStorage"
            ) byte[] content,
            @BindingName("name") String fileName,
            ExecutionContext context) {

        ReceivedMessageDto receivedMessageDto = new ReceivedMessageDto(content, fileName);
        serviceFunctionExample.serviceFunctionExample(receivedMessageDto);
    }
}
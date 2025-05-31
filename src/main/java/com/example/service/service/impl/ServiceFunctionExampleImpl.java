package com.example.service.service.impl;

import com.example.service.model.dto.PersonDto;
import com.example.service.model.dto.ReceivedMessageDto;
import com.example.service.model.entity.PersonEntity;
import com.example.service.repository.PersonRepository;
import com.example.service.service.ServiceFunctionExample;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class ServiceFunctionExampleImpl implements ServiceFunctionExample {

    private final PersonRepository personRepository;

    @Override
    public void serviceFunctionExample(ReceivedMessageDto receivedMessageDto) {
        log.info("::init process with file: {} ::", receivedMessageDto.fileName());

        try{
            PersonDto personDeserializedDto = convertByteToPersonDto(receivedMessageDto.content());
            PersonEntity personEntity = buildEntityFromDto(personDeserializedDto);
            personRepository.save(personEntity);

            Thread.sleep(2000);

            personRepository.findAll()
                    .forEach(person -> log.info("Person found: {}", person));

            log.info("::end process successfully with file: {} ::", receivedMessageDto.fileName());
        }catch (Exception e) {
            log.error("Error processing function:: {}", e.getMessage());
        }
    }

    private PersonDto convertByteToPersonDto(byte[] content) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(content, PersonDto.class);
        } catch (Exception e) {
            log.error("Error deserializing content to PersonDto", e);
            throw new RuntimeException("Failed to deserialize content", e);
        }
    }

    private PersonEntity buildEntityFromDto(PersonDto personDto) {
        return PersonEntity.builder()
                .idPerson(UUID.randomUUID().toString().replace("-", ""))
                .name(personDto.name())
                .firstName(personDto.firstName())
                .address(personDto.address())
                .build();
    }
}

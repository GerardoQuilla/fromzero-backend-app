package com.jgerardo.fromzeroapi.message.domain.model.commands;

public record AddMessageCommand (Long chatId, String senderId,  String content){
}

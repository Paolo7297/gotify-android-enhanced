package com.github.gotify.messages.provider;

import com.github.gotify.Settings;
import com.github.gotify.Utils;
import com.github.gotify.client.model.Application;
import com.github.gotify.client.model.Message;
import com.github.gotify.messages.Extras;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MessageImageCombiner {

    List<MessageWithImage> combine(List<Message> messages, List<Application> applications, Settings settings) {
        Map<Long, String> appIdToImage = appIdToImage(applications);

        List<MessageWithImage> result = new ArrayList<>();

        for (Message message : messages) {
            MessageWithImage messageWithImage = new MessageWithImage();

            messageWithImage.message = message;

            String imageUrl = Extras.getNestedValue(
                    String.class, message.getExtras(), "client::notification", "imageUrl");

            if (imageUrl != null) {
                messageWithImage.image = imageUrl;
            } else {
                messageWithImage.image =
                        Utils.resolveAbsoluteUrl(settings.url() + "/", appIdToImage.get(message.getAppid()));
            }

            result.add(messageWithImage);
        }

        return result;
    }

    public static Map<Long, String> appIdToImage(List<Application> applications) {
        Map<Long, String> map = new ConcurrentHashMap<>();
        for (Application app : applications) {
            map.put(app.getId(), app.getImage());
        }
        return map;
    }
}

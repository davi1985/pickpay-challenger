package dev.davisilva.picpay.service;

import dev.davisilva.picpay.client.NotificationClient;
import dev.davisilva.picpay.entity.Transfer;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private final NotificationClient notificationClient;

    public void sendNotification(Transfer transfer) {
        try {
            logger.info("Sending notification");

            var response = notificationClient.sendNotification(transfer);

            if (response.getStatusCode().isError()) {
                logger.error("Error while sending notification, status code is not OK");
            }

        } catch (Exception e) {
            logger.error("Error while sending notification", e);
        }
    }
}

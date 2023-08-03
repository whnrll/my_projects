package org.example.notifier;

import org.springframework.stereotype.Component;

import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import de.codecentric.boot.admin.server.domain.events.InstanceStatusChangedEvent;
import de.codecentric.boot.admin.server.notify.AbstractEventNotifier;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * 描述：报警通知
 *
 * @author xutao
 * @Date 2023-02-26 19:34:58
 */
@Slf4j
@Component
public class AlarmNotifier extends AbstractEventNotifier {

    protected AlarmNotifier(InstanceRepository repository) {
        super(repository);
    }

    /**
     * 描述：实现对事件的通知
     *
     * @param event 事件
     * @param instance 实例
     * @return {@link Mono }<{@link Void }>
     */
    @Override
    protected Mono<Void> doNotify(InstanceEvent event, Instance instance) {
        return Mono.fromRunnable(() -> {
            if (event instanceof InstanceStatusChangedEvent) {
                log.info("Instance status changed: [{}], [{}], [{}]", instance.getRegistration().getName(),
                    event.getInstance(), ((InstanceStatusChangedEvent)event).getStatusInfo().getStatus());
            } else {
                log.info("Instance info: [{}], [{}], [{}]", instance.getRegistration().getName(), event.getInstance(),
                    event.getType());
            }
        });
    }

}

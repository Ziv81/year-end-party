package com.changing.party.service;

import com.changing.party.common.enums.LogType;
import com.changing.party.model.ServiceLog;
import com.changing.party.model.UserModel;
import com.changing.party.repository.ServiceLogRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Log4j2
public class ServiceLogService {

    private ServiceLogRepository serviceLogRepository;

    public ServiceLogService(ServiceLogRepository serviceLogRepository) {
        this.serviceLogRepository = serviceLogRepository;
    }

    public void logService(LogType logType, UserModel user, int op, String content) {
        ServiceLog serviceLog = new ServiceLog();
        serviceLog.setLogTime(new Date());
        serviceLog.setLogType(logType);
        serviceLog.setOp(op);
        serviceLog.setLogContent(content);
        serviceLog.setUserModel(user);
        serviceLogRepository.save(serviceLog);
    }
}

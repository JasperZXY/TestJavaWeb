package zxy.web.controller.api;

import org.apache.commons.lang3.StringUtils;
import zxy.constants.EntityStatus;

public class BaseApiController {
    public Integer optionToStatus(String option) {
        if (StringUtils.isBlank(option)) {
            return null;
        }
        switch (option) {
            case "delete":
                return EntityStatus.DELETE;
            case "lock":
                return EntityStatus.FORBIDDEN;
            case "unlock":
                return EntityStatus.VALID;
        }
        return null;
    }
}

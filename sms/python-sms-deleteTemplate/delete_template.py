#! /usr/bin/env python
# -*- coding: utf-8 -*-

import logging

from baidubce.exception import BceServerError, BceHttpClientError
from baidubce.services.sms.sms_client import SmsClient

import sms_client_conf

# 设置日志
logging.basicConfig(level=logging.DEBUG)
logger = logging.getLogger(__name__)

# 创建SmsClient
sms_client = SmsClient(sms_client_conf.config)

# 根据模板ID删除对应的短信模板
try:
    template_id = "短信模板ID"
    sms_client.delete_template(template_id)
except BceHttpClientError as e:
    if isinstance(e.last_error, BceServerError):
        logger.error('delete template failed. Response %s, code: %s, msg: %s' % (
            e.last_error.status_code, e.last_error.code, e.last_error.message))
    else:
        logger.error('delete template failed. Unknown exception: %s' % e)

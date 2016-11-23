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

# 填写模板ID、模板参数值、接收者手机号码, 然后发送短信。
try:
    phone_number = ""  # 接收者的手机号码，仅支持单个号码
    invoke_id = ""  # 签名调用ID
    template_id = 'smsTpl:e7476122a1c24e37b3b0de19d04ae900'
    content_vars = {"code": "12345"}

    response = sms_client.send_message_2(invoke_id, template_id, phone_number, content_vars)
    print response.message_id
except BceHttpClientError as e:
    if isinstance(e.last_error, BceServerError):
        logger.error('send message failed. Response %s, code: %s, msg: %s' % (
            e.last_error.status_code, e.last_error.code, e.last_error.message))
    else:
        logger.error('send message failed. Unknown exception: %s' % e)

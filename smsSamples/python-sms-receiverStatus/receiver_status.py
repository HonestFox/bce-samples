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

# 查询单终端用户接收的短信信息。包括:
#   max_receive_per_phone_number_day -- 单日单终端用户的接收配额
#   received_today -- 单终端用户今日已接收的短信数量
try:
    phone_number = "要查询的终端用户的手机号码"
    response = sms_client.stat_receiver(phone_number)
    print response.max_receive_per_phone_number_day, response.received_today
except BceHttpClientError as e:
    if isinstance(e.last_error, BceServerError):
        logger.error('stat receiver failed. Response %s, code: %s, msg: %s' % (
            e.last_error.status_code, e.last_error.code, e.last_error.message))
    else:
        logger.error('stat receiver failed. Unknown exception: %s' % e)

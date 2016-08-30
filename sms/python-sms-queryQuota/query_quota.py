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

# 查询配额, 包括:
#   max_send_per_day -- 每天的发送配额
#   max_receive_per_phone_number_day -- 单日单终端用户的接收配额
#   sent_today -- 今日已发送的短信数量
try:
    quota = sms_client.query_quota()
    print quota.max_send_per_day, quota.max_receive_per_phone_number_day, quota.sent_today
except BceHttpClientError as e:
    if isinstance(e.last_error, BceServerError):
        logger.error('query quota failed. Response %s, code: %s, msg: %s' % (
            e.last_error.status_code, e.last_error.code, e.last_error.message))
    else:
        logger.error('query quota failed. Unknown exception: %s' % e)

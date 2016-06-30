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

# 填写短信流水号, 然后查询它对应的短信详情。主要包括:
#   message_id -- 短信流水号
#   receiver -- 短信接收者
#   content -- 短信内容
#   send_time -- 短信的发送时间
try:
    message_id = ""  # 短信流水号
    response = sms_client.query_message_detail(message_id)

    print response.message_id, response.receiver, response.content, response.send_time
except BceHttpClientError as e:
    if isinstance(e.last_error, BceServerError):
        logger.error('send message failed. Response %s, code: %s, msg: %s' % (
            e.last_error.status_code, e.last_error.code, e.last_error.message))
    else:
        logger.error('send message failed. Unknown exception: %s' % e)

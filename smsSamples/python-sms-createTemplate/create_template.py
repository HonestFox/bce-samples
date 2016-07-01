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

# 填写短信模板的名称和内容, 然后创建短信模板。
# 返回结果:
#   template_id -- 短信模板ID
try:
    template_name = "短信模板名称"
    template_content = "短信模板内容"
    response = sms_client.create_template(template_name, template_content)
    print response.template_id
except BceHttpClientError as e:
    if isinstance(e.last_error, BceServerError):
        logger.error('send message failed. Response %s, code: %s, msg: %s' % (
            e.last_error.status_code, e.last_error.code, e.last_error.message))
    else:
        logger.error('send message failed. Unknown exception: %s' % e)

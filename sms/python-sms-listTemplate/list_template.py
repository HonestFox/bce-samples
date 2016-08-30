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

# 列出全部短信模板
# 返回结果中的template_list是用户自定义模板的列表, 其中每个元素是一个短信模板, 包括:
#   template_id -- 模板ID
#   name -- 模板名称
#   content -- 模板内容
#   status -- 模板状态(VALID/INVALID)
#   create_time -- 模板创建时间
#   update_time -- 模板更新时间
try:
    response = sms_client.get_template_list()

    for template in response.template_list:
        print template.template_id, template.name, template.content, template.status, template.create_time, \
              template.update_time
except BceHttpClientError as e:
    if isinstance(e.last_error, BceServerError):
        logger.error('list template failed. Response %s, code: %s, msg: %s' % (
            e.last_error.status_code, e.last_error.code, e.last_error.message))
    else:
        logger.error('list template failed. Unknown exception: %s' % e)

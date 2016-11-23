#! /usr/bin/env python
# -*- coding: utf-8 -*-

# 导入Python标准日志模块
import logging

# 从Python SDK导入SMS配置管理模块以及安全认证模块
from baidubce.bce_client_configuration import BceClientConfiguration
from baidubce.auth.bce_credentials import BceCredentials

# 设置SmsClient的Host、AK、SK
# 注意：如果是gz区域的BCC用户，host应该使用 sms.gz.baidubce.com
#       如果是其他区域的BCC用户，或者非BCC用户，则使用 sms.bj.baidubce.com
host = "sms.bj.baidubce.com"
AK = ""
SK = ""

# 设置日志文件的句柄和日志级别
logger = logging.getLogger("baidubce.services.sms.smsclient")
fh = logging.FileHandler("sms_sample.log")
fh.setLevel(logging.DEBUG)

# 设置日志文件输出的顺序、结构和内容
formatter = logging.Formatter("%(asctime)s - %(name)s - %(levelname)s - %(message)s")
fh.setFormatter(formatter)
logger.setLevel(logging.DEBUG)
logger.addHandler(fh)

# 创建BceClientConfiguration
config = BceClientConfiguration(credentials=BceCredentials(AK, SK), endpoint=host)

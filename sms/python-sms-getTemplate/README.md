# python-sms-getTemplate

## 用途：

查询短信模板的详情（**仅限用户自定义模板**），包括：

* template_id -- 模板ID
* name -- 模板名称
* content -- 模板内容
* status -- 模板状态（VALID -- 审核通过，INVALID -- 审核未通过）
* create_time -- 模板创建时间
* update_time -- 模板更新时间

**注意：仅适用于Python 2.7**

## 使用方法：

* 第一步：在文件`sms_client_conf.py`中配置AK/SK。
* 第二步：在文件`get_template.py`中填写要查询的短信模板ID。
* 第三步：执行文件`get_template.py`。

## 代码简介：

### 第一步：安装BCE Python SDK。

请参考：[百度开放云 官方文档](https://bce.baidu.com/doc/SMS/Python-SDK.html#.E5.AE.89.E8.A3.85SDK.E5.B7.A5.E5.85.B7.E5.8C.85)

### 第二步：创建配置文件`sms_client_conf.py`，并配置AK/SK。

```python
#! /usr/bin/env python
# -*- coding: utf-8 -*-

# 从Python SDK导入SMS配置管理模块以及安全认证模块
from baidubce.bce_client_configuration import BceClientConfiguration
from baidubce.auth.bce_credentials import BceCredentials

# 设置SmsClient的Host、AK、SK
host = "sms.bj.baidubce.com"
AK = ""
SK = ""

# 创建BceClientConfiguration
config = BceClientConfiguration(credentials=BceCredentials(AK, SK), endpoint=host)
```

### 第三步：创建SmsClient。

```python
sms_client = SmsClient(sms_client_conf.config)
```

### 第四步：查询某个短信模板的信息。

```python
try:
    template_id = "短信模板ID"
    template = sms_client.get_template_detail(template_id)
    print template.template_id, template.name, template.content, template.status, template.create_time, \
          template.update_time
except BceHttpClientError as e:
    if isinstance(e.last_error, BceServerError):
        logger.error('get template failed. Response %s, code: %s, msg: %s' % (
            e.last_error.status_code, e.last_error.code, e.last_error.message))
    else:
        logger.error('get template failed. Unknown exception: %s' % e)
```
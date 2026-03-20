# OpenAI Code Review - 初始化项目包 v1.0

## 项目说明
本项目是从原始 `openai-code-review` 复刻并经过「私有化改造」后的版本。所有原作者相关的标识、包名、Group ID 及硬编码的业务 ID 已全部剔除，代之以标准化的占位符或新的私有化标识。

## 改造内容
1. **包名重构**：由 `plus.gaga.middleware` 统一重构为 `com.ai.reviewer`。
2. **Maven 坐标**：`groupId` 已修改为 `com.ai.reviewer`。
3. **标识清理**：移除了代码注释中的原作者信息、GitHub 仓库地址及个人微信号。
4. **配置抽象**：对硬编码的微信 AppID、用户 ID 等进行了占位符处理，需在运行时通过环境变量注入。

## 运行环境
- JDK 1.8+
- Maven 3.6+
- GitHub Actions (可选)

## 快速开始
1. **本地构建**：
   ```bash
   mvn clean install
   ```
2. **配置 Secrets**：
   若使用 GitHub Actions，请在仓库 `Settings -> Secrets -> Actions` 中配置以下内容：
   - `CODE_TOKEN`: GitHub Personal Access Token
   - `CODE_REVIEW_LOG_URI`: 用于存放评审日志的仓库地址
   - `WEIXIN_APPID`: 微信公众号 AppID
   - `WEIXIN_SECRET`: 微信公众号 Secret
   - `WEIXIN_TOUSER`: 接收通知的微信用户 OpenID
   - `WEIXIN_TEMPLATE_ID`: 微信模板消息 ID
   - `CHATGLM_APIKEYSECRET`: ChatGLM API Key

## 核心逻辑
项目保持了原有的核心逻辑：
- 自动检测 Git 提交差异。
- 调用大模型进行代码评审。
- 通过微信公众号发送评审结果。

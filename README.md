1.通过spring boot开发项目，项目结构包含controller,mapper,service层。
项目接口包含五个以上，能够使用mybatis对数据库进行增删改查等操作。
2.项目内容包含定时任务自动化定时构建和当代码提交时自动化构建（通过Jenkins Hash的crontab表达式计算时间）。
3.多节点集群构建。
4.能自动化通过dockerfile构建镜像。
5.将dockerfile构建好的镜像推送到阿里云公有仓库和本地私有仓库。
6.自动化打包通过docker run运行容器。
7.自动化通过docker-compose打包运行容器（如果多个服务之间有依赖关系，需要在docker-compose里面体现）。
8.能够完成pipeline自动化构建任务，并且里面需要使用到docker-compose。里面需要包含BlueOcean不同阶段重试机制。
9.能够完成Jenkins的多分支构建，拉取gitlab不同分支代码，打包构建将镜像容器发往不同测试服务器执行。
10.代码功能完善，复杂度偏高，自己原创，满足上线需求，答辩过程表达清晰。
11.进行docker容器的监控。

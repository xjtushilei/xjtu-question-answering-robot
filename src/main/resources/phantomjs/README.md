# 爬虫驱动器

在war包部署时候，进行初始化的时候，在start.StartOfOS.java中代码会检查并设置使用哪个

- phantomjs 是linux下用的
- phantomjs.exe 是windows底下用的

## 其他

在docker环境下，或者有些linux环境下，会缺少一些系统的lib，需要安装，比如：`RUN yum install -y fontconfig`。其他的可以去官网查看！

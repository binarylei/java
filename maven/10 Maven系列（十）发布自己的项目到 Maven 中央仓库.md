# Maven系列（十）发布自己的项目到 Maven 中央仓库

可能很多人都在用maven仓库，但是如果要问怎么发布项目到中央仓库，估计很多人都不知道了，下面本篇文章带大家往中央仓库发布一个自己的maven项目。

往maven中央仓库发布组件的过程是与Sonatype工作人员交互的过程，这个过程是在Sonatype的JIRA平台上完成的，过程如下：

1. 在Sonatype的JIRA注册

2. 提交一个issue（提出一个发布申请），告诉工作人员我要创建一个构件。

3. 等待工作人员审批，会给你发邮件，在这个issue下给你comment说明通过或者哪里有问题。

4. 上传构件

5. 发布构建，并在哪个issue下告诉工作人员我发布了

6. 等待审核，如果通过会告诉你需要release一下并在issue上告诉工作人员我release了

7. 发布成功

8. 后续更新

## 1. 注册帐户

注册地址：https://issues.sonatype.org/secure/Signup!default.jspa

使用了JIRA来管理流程，记住用户名和密码，后面会用到。

此外，Sonatype 还提供了一个名为 OSS 的系统，具体的构件发布是在这个oss系统上，

Sonatype OSS 地址：https://oss.sonatype.org

这里的用户名和密码就是上面在JIRA中注册的，在这里可以查询到全世界已发布的构件，当然我们发布构件的操作也在这里进行。

## 2. 创建一个 Issue

地址是 https://issues.sonatype.org/secure/CreateIssue.jspa?issuetype=21&pid=10134

![创建一个 Issue](http://img.blog.csdn.net/20170613135453544?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXFfMjcwMjg1NjE=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center)

这里需要特别说明的是Group Id，如果你是托管在Github或者Git@OSC 可以使用com.github.binarylei或者net.oschina.XXX，，剩下的可以依照实际情况填写，例如托管的地址等等（托管地址等信息会在用去去maven仓库搜索的时候显示，用来帮助用户找到你的项目地址寻求帮助）。另外此处填写的group id必须和你要发布的组件的pom中的group id一样，必须一样！！！

## 3. 等待Issue审批

这个审核还是很及时的，我的几分钟后就收到审核结果。如果不通过审核人员会告诉你不通过的原因，例如当你用的是自己的域名时（不是github）审核人员会询问你域名是否是你的，回复一下就行。审核通过后会收到邮件通知，同时在Issue下面会看到 Sonatype 工作人员的回复，一般是添加一个comment，内容大致如下：

```
com.github.binarylei has been prepared, now user(s) binarylei can:
* Deploy snapshot artifacts into repository https://oss.sonatype.org/content/repositories/snapshots
* Deploy release artifacts into the staging repository https://oss.sonatype.org/service/local/staging/deploy/maven2
* Promote staged artifacts into repository 'Releases'
* Download snapshot and release artifacts from group https://oss.sonatype.org/content/groups/public
* Download snapshot, release and staged artifacts from staging group https://oss.sonatype.org/content/groups/staging
```

这个是工作人员告诉我已经通过了，可以去下面那个地址发布（前两个地址），发布后去下面后两个地址搜索。


## 4. 使用 GPG 生成密钥对

Windows 系统，可以下载 Gpg4win 软件来生成密钥对。下载地址：https://www.gpg4win.org/download.html

推荐使用 Gpg4win-Vanilla 版本，因为它仅包括 GnuPG，这个工具才是我们所需要的。

Linux 系统，直接从源中安装gpg软件包就行。[更多GnuPG使用详情](http://www.ruanyifeng.com/blog/2013/07/gpg.html)

安装 GPG 软件后，打开命令行窗口，依次做以下操作：

（1）查看是否安装成功

    gpg --version

出现版本等信息说明安装成功了。

（2）生成密钥对

    ```
    gpg --gen-key

    Real name: binarylei
    Email address: binarylei@qq.com
    You selected this USER-ID:
        "binarylei <binarylei@qq.com>"

    Change (N)ame, (E)mail, or (O)kay/(Q)uit? o
    ```

之后往下，会让你输入用户名和邮箱，还有一个Passphase（输入两次），相当于密钥库密码，不要忘记。

（3）查看公钥

    ```
    gpg --list-keys

    C:/Users/len/AppData/Roaming/gnupg/pubring.kbx
    ----------------------------------------------
    pub   rsa2048 2017-12-16 [SC] [expires: 2019-12-16]
          9DF36BF5DFB87B6F04DBCE3D63EC6544BEE6682D
    uid           [ultimate] binarylei <binarylei@qq.com>
    sub   rsa2048 2017-12-16 [E] [expires: 2019-12-16]
    ```

我这里的公钥ID是9DF36BF5DFB87B6F04DBCE3D63EC6544BEE6682D，马上就会用到了。

（3）将公钥发布到 PGP 密钥服务器

    gpg --keyserver hkp://pool.sks-keyservers.net --send-keys 9DF36BF5DFB87B6F04DBCE3D63EC6544BEE6682D

    gpg: sending key 63EC6544BEE6682D to hkp server pool.sks-keyservers.net

此后，可使用本地的私钥来对上传构件进行数字签名，而下载该构件的用户可通过上传的公钥来验证签名，也就是说，大家可以验证这个构件是否由本人上传的，因为有可能该构件被坏人给篡改了。

（4）查询公钥是否发布成功

    gpg --keyserver hkp://pool.sks-keyservers.net --recv-keys  9DF36BF5DFB87B6F04DBCE3D63EC6544BEE6682D

    gpg: key 63EC6544BEE6682D: "binarylei <binarylei@qq.com>" not changed
    gpg: Total number processed: 1
    gpg:              unchanged: 1

## 5. 修改Maven配置文件

### setting.xml

找到maven的全局配置文件settings.xml，在里面找到 节点，这个节点默认是注释掉的，增加如下配置：

```xml
<servers>
    <server>
        <id>oss</id>
        <username>用户名</username>
        <password>密码</password>
    </server>
</servers>
```

这里的id是要在pom.xml里面使用的，用户名和密码就是在Sonatype上面注册的用户名和密码。

### pom.xml

```xml
<project>
    ...
    <name>dexcoder-assistant</name>
    <description>dexcoder-assistant is a rapid development kit.</description>
    <url>http://www.dexcoder.com/</url>
    <licenses>
        <license>
            <name>The Apache Software License, Version 2.0</name>
            <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>
        </license>
    </licenses>
    <developers>
        <developer>
            <name>selfly</name>
            <email>selfly@foxmail.com</email>
        </developer>
    </developers>
    <scm>
        <connection>scm:git:git@github.com:selfly/dexcoder-assistant.git</connection>
        <developerConnection>scm:git:git@github.com:selfly/dexcoder-assistant.git</developerConnection>
        <url>git@github.com:selfly/dexcoder-assistant.git</url>
    </scm>
    
    <profiles>
        <profile>
            <id>release</id>
            <build>
                <plugins>
                    <!-- Source -->
                    <plugin>
                        <groupId>org.apache.maven.plugins</groupId>
                        <artifactId>maven-source-plugin</artifactId>
                        <version>2.2.1</version>
                        <executions>
                            <execution>
                                <phase>package</phase>
                                <goals>
                                    <goal>jar-no-fork</goal>
                                </goals>
                            </execution>
                        </executions>
                    </plugin>
                    <!-- Javadoc -->
                    <plugin>
                        <groupId>org.apache.maven.plugins</groupId>
                        <artifactId>maven-javadoc-plugin</artifactId>
                        <version>2.9.1</version>
                        <executions>
                            <execution>
                                <phase>package</phase>
                                <goals>
                                    <goal>jar</goal>
                                </goals>
                            </execution>
                        </executions>
                    </plugin>
                    <!-- GPG -->
                    <plugin>
                        <groupId>org.apache.maven.plugins</groupId>
                        <artifactId>maven-gpg-plugin</artifactId>
                        <version>1.5</version>
                        <executions>
                            <execution>
                                <phase>verify</phase>
                                <goals>
                                    <goal>sign</goal>
                                </goals>
                            </execution>
                        </executions>
                    </plugin>
                </plugins>
            </build>
            <distributionManagement>
                <snapshotRepository>
                    <id>oss</id>
                    <url>https://oss.sonatype.org/content/repositories/snapshots/</url>
                </snapshotRepository>
                <repository>
                    <id>oss</id>
                    <url>https://oss.sonatype.org/service/local/staging/deploy/maven2/</url>
                </repository>
            </distributionManagement>
        </profile>
    </profiles>
    ...
</project>
```

pom.xml中必须包括：name、description、url、licenses、developers、scm 等基本信息，使用了 Maven 的 profile 功能，只有在 release 的时候，创建源码包、文档包、使用 GPG 进行数字签名。

此外，snapshotRepository 与 repository 中的 id 一定要与 setting.xml 中 server 的 id 保持一致。

如果是多模块项目的话，只需要在父pom.xml中声明这些，子pom.xml中只需要修改相应的一些信息，如name标签。

## 6. 上传构件到 OSS 中

    mvn clean deploy -P release

当执行以上 Maven 命令时，会自动弹出一个对话框，需要输入上面提到的 Passphase，它就是刚才设置的 GPG 密钥库的密码。

随后会看到大量的 upload 信息，因为在国内网络的缘故，时间有点久，耐心等待吧。

注意：此时上传的构件并未正式发布到中央仓库中，只是部署到 OSS 中了，下面才是真正的发布。

## 7. 在 OSS 中发布构件

使用 Sonatype 账号登录 https://oss.sonatype.org/#stagingRepositories，可在 Staging Repositories 中查看刚才已上传的构件。
一般发布的构件不多，可以直接拉到底就能看到自己的构件，也可进行模糊查询定位到自己的构件（见后边附图）。

![Close构件](http://img.blog.csdn.net/20170109182005841?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvc2hlbGxkb24=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center)

最后，点击 Release 按钮来发布该构件。手动刷新后看到已经发布。

![Release构件](http://img.blog.csdn.net/20170109182034435?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvc2hlbGxkb24=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center)

## 8. 通知 Sonatype 构件已成功发布

这个前面的Sonatype工作人员其实在审核你的Issue时，在comment中已经提示你了，

在Issue下面回复一条“构件已成功发布”的评论，这是为了通知 Sonatype 的工作人员为需要发布的构件做审批，发布后会关闭该Issue。

## 9. 等待构件审批通过

这个，又只能等待了，当然他们晚上上班，还是第二天看。当审批通过后，将会收到邮件通知。

## 10. 从中央仓库中搜索构件

这时，就可以在maven的中央仓库中搜索到自己发布的构件了，以后可以直接在pom.xml中使用了！

中央仓库搜索网站：http://search.maven.org/

第一次成功发布之后，以后就不用这么麻烦了，可以直接使用Group Id发布任何的构件，当然前提是Group Id没有变。

## 11. 以后的发布流程：

a）构件完成后直接使用maven在命令行上传构建；

b）在https://oss.sonatype.org/ close并release构件；

c)等待同步好（大约2小时多）之后，就可以使用了





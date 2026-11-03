# Nelement (Fork of Element Android)

Nelement是一个Element Android的fork，旨在通过NerChat!为中国大陆用户提供Element开源服务器的安全、加密的聊天服务。

欢迎访问[NerChat!文档](https://www.neboer.site/nerchat/)

## Nelement对Element Android的修改

Nelement主要修改了Element Android的四处代码：

1. Nelement修改了Element Android的家服务器配置文件，修改默认服务器、bug报告包名等信息。

   参考这个[commit](https://github.com/Neboer/Nelement/commit/30857e2f)的这个[文件修改](https://github.com/Neboer/Nelement/commit/30857e2f#diff-5db79790a9dbb104c9d1687b291b21b55bc9ae1736d6a83251e1de6a012911bf)。


2. Nelement修改了Element Android的图标、包名和应用名，与原版软件做出了重要区分，并且可以和原软件共存。

   对图标和应用名的修改请参考同一个[commit](https://github.com/Neboer/Nelement/commit/30857e2f)（与上一个相同）的其余所有修改。

   对包名的修改请参考这个[commit](https://github.com/Neboer/Nelement/commit/b5a5a4e8)中的这个[文件修改](https://github.com/Neboer/Nelement/commit/b5a5a4e8#diff-7d90e090118cb3b19ed7934b93c3b7ce8235f64f4fe186d2ea3f83fee00bf03e)


3. Nelement修改了Element Android的构建流程，直接构建Fdroid版本，而不再编译Google Play的预编译版本，因为Nelement不会发布到Google Play商店，同时也避免了无Google Play框架的国产手机在使用中出现问题。

   这个修改主要发生在这个[commit](https://github.com/Neboer/Nelement/commit/afc7636f)，这里删除了Element Android中不必要的测试/发布等CI过程，只保留Fdroid Release版本的构建代码。

   后来我们又在[这个提交](https://github.com/Neboer/Nelement/commit/991ef67676a6347f1023c9e1b789b375356083b9)中将Google Play构建添加了回来，因为我们需要对FCM提供的支持。


5. Nelement修改了Element Android中配置的默认jitsi会议服务器地址为我们在国内搭建的jitsi服务，让用户可以享受高品质、低延迟的语音通话体验。

   参考这个[commit](https://github.com/Neboer/Nelement/commit/5efdb741)。

6. （最新更新）Nelement支持了我们自建的Unified Push Gateway和基于FCM的通知服务器！现在，无论你的手机是否支持Google服务框架，你都可以获得很好的即时通知体验了！

   在这个[commit](https://github.com/Neboer/Nelement/commit/30aec3492b13e7ae3272ae13abd2f37b1e2e6f2b)中，我们支持了FCM。

   在这个[commit](https://github.com/Neboer/Nelement/commit/ac9042fb027ba0218b352764e1cbc48ca5763cec)中，我们支持了Unified Push。

我们还对Nelement做了一些其他的适配性小修改，比如修改google-services.json中的包名、修改manifest文件中注册的，以及使用Neboer自己的密钥对Nelement进行签名、修改Element的版本号等等。

以下是原README文件内容。

[![Latest build](https://github.com/element-hq/element-android/actions/workflows/build.yml/badge.svg?query=branch%3Adevelop)](https://github.com/element-hq/element-android/actions/workflows/build.yml?query=branch%3Adevelop)
[![Weblate](https://translate.element.io/widgets/element-android/-/svg-badge.svg)](https://translate.element.io/engage/element-android/?utm_source=widget)
[![Element Android Matrix room #element-android:matrix.org](https://img.shields.io/matrix/element-android:matrix.org.svg?label=%23element-android:matrix.org&logo=matrix&server_fqdn=matrix.org)](https://matrix.to/#/#element-android:matrix.org)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=element-android&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=element-android)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=element-android&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=element-android)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=element-android&metric=bugs)](https://sonarcloud.io/summary/new_code?id=element-android)

# Element Android

Element Classic Android is a previous-generation [Matrix](https://matrix.org/) client provided by [Element](https://element.io/). The app can be run on every Android devices with Android OS Lollipop and more (API 21). This client is still supported and receives security updates but no new features or usability enhancements are made. It is recommended to use [Element X](https://github.com/element-hq/element-x-android) that is the next-generation mobile app.

[<img src="resources/img/google-play-badge.png" alt="Get it on Google Play" height="60">](https://play.google.com/store/apps/details?id=im.vector.app)
[<img src="resources/img/f-droid-badge.png" alt="Get it on F-Droid" height="60">](https://f-droid.org/app/im.vector.app)

Build of develop branch: [![GitHub Action](https://github.com/element-hq/element-android/actions/workflows/build.yml/badge.svg?query=branch%3Adevelop)](https://github.com/element-hq/element-android/actions/workflows/build.yml?query=branch%3Adevelop) Nightly test status: [![allScreensTest](https://github.com/element-hq/element-android/actions/workflows/nightly.yml/badge.svg)](https://github.com/element-hq/element-android/actions/workflows/nightly.yml)


# New Android SDK

Element is based on a new Android SDK fully written in Kotlin (like Element). In order to make the early development as fast as possible, Element and the new SDK currently share the same git repository.

At each Element release, the SDK module is copied to a dedicated repository: https://github.com/matrix-org/matrix-android-sdk2. That way, third party apps can add a regular gradle dependency to use it. So more details on how to do that here: https://github.com/matrix-org/matrix-android-sdk2.

# Roadmap

The version 1.0.0 of Element still misses some features which was previously included in Riot-Android.
The team will work to add them on a regular basis.

# Releases to app stores

There is some delay between when a release is created and when it appears in the app stores (Google Play Store and F-Droid). Here are some of the reasons:

* Not all versioned releases that appear on GitHub are considered stable. Each release is first considered beta: this continues for at least two days. If the release is stable (no serious issues or crashes are reported), then it is released as a production release in Google Play Store, and a request is sent to F-Droid too.
* Each release on the Google Play Store undergoes review by Google before it comes out. This can take an unpredictable amount of time. In some cases it has taken several weeks.
* In order for F-Droid to guarantee that the app you receive exactly matches the public source code, they build releases themselves. When a release is considered stable, Element staff inform the F-Droid maintainers and it is added to the build queue. Depending on the load on F-Droid's infrastructure, it can take some time for releases to be built. This always takes at least 24 hours, and can take several days.

If you would like to receive releases more quickly (bearing in mind that they may not be stable) you have a number of options:

1. [Sign up to receive beta releases](https://play.google.com/apps/testing/im.vector.app) via the Google Play Store.
2. Install a [release APK](https://github.com/element-hq/element-android/releases) directly - download the relevant .apk file and allow installing from untrusted sources in your device settings.  Note: these releases are the Google Play version, which depend on some Google services.  If you prefer to avoid that, try the latest dev builds, and choose the F-Droid version.
3. If you're really brave, install the [very latest dev build](https://github.com/element-hq/element-android/actions/workflows/build.yml?query=branch%3Adevelop) - pick a build, then click on `Summary` to download the APKs from there: `vector-Fdroid-debug` and `vector-Gplay-debug` contains the APK for the desired store. Each file contains 5 APKs. 4 APKs for every supported specific architecture of device. In doubt you can install the `universal` APK.

## Contributing

Please refer to [CONTRIBUTING.md](./CONTRIBUTING.md) if you want to contribute on Matrix Android projects!

Come chat with the community in the dedicated Matrix [room](https://matrix.to/#/#element-android:matrix.org).

Also [this documentation](./docs/_developer_onboarding.md) can hopefully help developers to start working on the project.

## Triaging issues

Issues are triaged by community members and the Android App Team, following the [triage process](https://github.com/element-hq/element-meta/wiki/Triage-process).

We use [issue labels](https://github.com/element-hq/element-meta/wiki/Issue-labelling) to sort all incoming issues.

## Copyright and License

Copyright (c) 2018 - 2025 New Vector Ltd

This software is dual licensed by New Vector Ltd (Element). It can be used either:

(1) for free under the terms of the GNU Affero General Public License (as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version); OR

(2) under the terms of a paid-for Element Commercial License agreement between you and Element (the terms of which may vary depending on what you and Element have agreed to).

Unless required by applicable law or agreed to in writing, software distributed under the Licenses is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the Licenses for the specific language governing permissions and limitations under the Licenses.

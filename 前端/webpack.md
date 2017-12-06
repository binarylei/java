# webpack

> [WebPack](https://doc.webpack-china.org/) 可以看做是**模块打包机**：它做的事情是，分析你的项目结构，找到JavaScript模块以及其它的一些浏览器不能直接运行的拓展语言（Scss，ES6），并将其转换和打包为合适的格式供浏览器使用。

## 安装
	
	```
	//全局安装
	npm install -g webpack
	//安装到你的项目目录
	npm install --save-dev webpack
	```
	
## 配制

创建 `webpack.config.js` ，它的作用和 `pulpfile.js` 一样是一个配置项，设置 webpack 任务功能。

* `entry` 入口文件，让 webpack 用哪个文件作为项目的入口
* `output` 出口，让 webpack 把处理完成的文件放在哪里
* `module` 模块，要用什么不同的模块来处理各种类型的文件
* `plugins` 插件
* `reslove` 用来设置路径指向
* `watch` 监听文件有改动后执行打包

```js
module.exports = {
  entry: './module/main.js',
  output: {
    filename: './dist/js/a.js',
  },
  module: {
  	loaders: {
  		{test: /\.js$/, loader: ''}
  	}
  },
  plugins: {},
  reslove: {},
  watch: true
}
```

## 快速入门

1. 新建一个[项目](https://doc.webpack-china.org/)，目录结构如下

```
webpack
  |---module
  |     |---main.js
  |     |---show.js
  |
  |---webpack.config.js
```

2. 代码如下：

	**main.js**

	```
	var module1 = require('./show');

	module1.show();
	```

	**show.js**

	```
	exports.show = function() {
		console.log("show...");
	}
	```

	配制文件：**webpack.config.js**

	```
	module.exports = {
	  entry: './module/main.js',
	  output: {
	    filename: './dist/js/a.js',
	  },
	  watch: true //实时监听，文件发生变化时，自动刷新
	}
	```

3. 打包，`--watch` 为可选参数，同样可实现动态监听

	webpack --wach

	打包成功后，项目目录下出现一个 `dist` 文件夹

## 打包js

```
npm install webpack --save-dev
```

```
const webpack = require('webpack');

module.exports = {
  entry: {
  	bundle1: './module/bundle1.js',
  	bundle2: './module/bundle2.js',
  },
  output: {
    filename: './dist/js/[name].js',
  },
  plugins: [
  	//打包公共的js文件
  	new webpack.optimize.CommonsChunkPlugin('commons')
  ],
  watch: true
}
```

### es6

  //打包ES6语法
  cnpm install babel-loader --save-dev

```
const webpack = require('webpack');

module.exports = {
  entry: {
    bundle1: './module/bundle1.js'
  },
  output: {
    filename: './dist/js/[name].js',
  }，
  module: {
    loaders: [
      { test: /\.(js)$/, loaders: 'babel-loader' },
    ]
  }
}
```

## 打包样式和转换less或sass

```
// 安装CSS模块
npm install style-loader css-loader --save-dev
// 安装LESS模块
npm install less-loader less --save-dev
// 安装SASS模块
npm install sass-loader node-sass --save-dev
```

配制文件：**webpack.config.js**

```module.exports = {
  entry: {
  	bundle1: './module/bundle1.js'
  },
  output: {
    filename: './dist/js/[name].js',
  },
  module: {
    loaders: [
      { test: /\.(css|less)$/, loaders: 'style-loader!css-loader!less-loader' },
    ]
  }
}
```

打包生成 css 文件

```
const webpack = require('webpack');
const ExtractTextPlugin = require('extract-text-webpack-plugin');
const extractCSS = new ExtractTextPlugin('dist/css/[name].css');

module.exports = {
  entry: {
  	bundle1: './module/main.js'
  },
  output: {
    filename: './dist/js/[name].js',
  },
  module: {
    loaders: [
      { test: /\.(css|less)$/, loaders: extractCSS.extract(['css-loader', 'less-loader']) },
    ]
  },
  plugins: [
  	new webpack.optimize.CommonsChunkPlugin('commons'),
  	extractCSS
  ],
  watch: true
}
```

## 参考

1. [Webpack入门](http://www.jianshu.com/p/42e11515c10f)

2. [Webpack官网](https://doc.webpack-china.org/)

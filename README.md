# DropPopAlert

<p align="center">
    <img src="https://imgur.com/eUkrzG6.gif" width="300">
</p>

上、下からアニメーションしながら出てくるView。  

# 導入

JitPackから導入できます。

[![](https://jitpack.io/v/takusan23/DropPopAlert.svg)](https://jitpack.io/#takusan23/DropPopAlert)


`app`フォルダじゃない方の`build.gradle`を開き、一行書き足します。

```gradle
allprojects {
    repositories {
        google()
        jcenter()
        // こいつ
    	maven { url 'https://jitpack.io' }
   }
}
```

`app`フォルダの方の`build.gradle`を開き、`dependencies { }`の中に書き足します。

`1.0.0`のところは最新版( https://github.com/takusan23/DropPopAlert/releases )を入れてください。

```gradle
dependencies {
    // これも
    implementation 'com.github.takusan23:DropPopAlert:1.0.0'
}
```

# 使い方

今回は`ViewBinding`を使います。`ViewBinding`を有効にするには以下のように`app/build.gradle`に書いてください。

```gradle
android {
    // これと
    viewBinding {
        enabled = true
    }
}

dependencies {
    // これ
    implementation 'com.github.takusan23:DropPopAlert:1.0.0'
}
```

# Viewが一つの場合

TextViewだけ動かす場合は、わざわざレイアウト

## 1.レイアウト

下、上どちらかに固定している必要があります(ConstraintLayoutだと`layout_constraintStart_toStartOf`とか？。FrameLayoutだと`layout_gravity="top"`とか？)

以下は一例

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <Button
        android:id="@+id/drop_simple_button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Simple"
        app:layout_constraintEnd_toEndOf="@+id/drop_down_button"
        app:layout_constraintStart_toStartOf="@+id/drop_down_button"
        app:layout_constraintTop_toBottomOf="@+id/drop_down_button" />

    <TextView
        android:id="@+id/drop_pop_alert_simple"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:background="#FF7C4E"
        android:text="Viewが一つの場合は、\nView#toDropPopAlert() でもアニメーションが利用できます"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

## 2.Kotlinを書く

`DropPopAlert(動かすView)`もしくは、`動かすView.toDropPopAlert()`で利用できるようになります。  
後者はKotlinの拡張関数を利用しています。が特にやってることは同じです

できたら`alert()`するなり、`showAlert()`を利用するなりできます。

```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBindingを利用している。Kotlin Android Extensionsが非推奨になったので
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Viewが一つの場合は、DropPopAlertViewを利用せずに、DropPopAlertを利用したほうがいい
        val simpleDropPopAlert = binding.dropPopAlertSimple.toDropPopAlert()
        binding.dropSimpleButton.setOnClickListener {
            simpleDropPopAlert.alert(DropPopAlert.ALERT_DROP)
        }
  }
}
```

### 注意点
`Visibility`が`GONE`になります。

# Viewが複数の場合

FrameLayoutを継承した`DropPopAlertView`を利用することで複数のViewを同時に移動できます（上のGIFでは上から出てくるやつ）  

## 1.DropPopAlertViewをレイアウトに置きます

以下は一例  
下、上どちらかに固定している必要があります(ConstraintLayoutだと上/下に制約。FrameLayoutだと`layout_gravity="top"`とか？)

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <Button
        android:id="@+id/drop_down_button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="DROP_DOWN"
        android:textAllCaps="false"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <io.github.takusan23.droppopalert.DropPopAlertView
        android:id="@+id/drop_pop_alert_up_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="#7CFFEE"
        android:visibility="gone"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        tools:visibility="visible">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical">

            <TextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_margin="2dp"
                android:text="Drop Pop Alert"
                android:textSize="20sp" />
        </LinearLayout>
    </io.github.takusan23.droppopalert.DropPopAlertView>

</androidx.constraintlayout.widget.ConstraintLayout>
```

### 注意点
- `android:visibility`を`gone`にしておく必要があります。
- `tools:visibility`は、`visible`することでレイアウトエディタ上では見ることができるようになります。

## 2.Kotlinを書く

`DropPopAlertView#alert()`でアニメーションしながら表示されます。  
引数
- `position`
    - このどれか(現状)
    - `DropPopAlert.ALERT_UP`
        - 下から飛び出てくるアニメーションをするにはこれ
    - `DropPopAlert.ALERT_DROP`
        - 上から降りてくるアニメーションをするにはこれ

```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBindingを利用している。Kotlin Android Extensionsが非推奨になったので
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 基本的な使い方
        binding.dropUpButton.setOnClickListener {
            binding.dropPopAlertUpView.alert(DropPopAlert.ALERT_UP)
        }
    }
}
```

書けたら実行してみてください。

# 応用編

## 表示時間

`DropPopAlert#showTimeMs`で自由に変更できます。単位はミリ秒です。  

例：

```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBindingを利用している。Kotlin Android Extensionsが非推奨になったので
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 5秒
        binding.dropPopAlertUpView.dropPopAlert.showTimeMs = 5 * 1000

        // 基本的な使い方
        binding.dropUpButton.setOnClickListener {
            binding.dropPopAlertUpView.alert(DropPopAlert.ALERT_UP)
        }
    }
}
```

## 任意のタイミングで表示させたり、非表示にさせることができます。

| 関数名                         | 引数                            | 説明                                                                  |
|--------------------------------|---------------------------------|-----------------------------------------------------------------------|
| `DropPopAlert#showAlert()` | `DropPopAlert.ALERT_UP`など | 表示させるだけの関数です。自動で非表示にはなりません。                |
| `DropPopAlert#hideAlert()` | `DropPopAlert.ALERT_UP`など | 非表示にするだけの関数です。`showAlert()`を消すときに使ってください。 |

例：

```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBindingを利用している。Kotlin Android Extensionsが非推奨になったので
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 5秒
        binding.dropPopAlertUpView.dropPopAlert.showTimeMs = 5 * 1000

        // 基本的な使い方
        binding.dropUpButton.setOnClickListener {
            // 表示/非表示を切り替える
            if (binding.dropPopAlertUpView.isVisible) {
                binding.dropPopAlertUpView.hideAlert(DropPopAlert.ALERT_UP)
            } else {
                binding.dropPopAlertUpView.showAlert(DropPopAlert.ALERT_UP)
            }
        }
    }
}
```

## DropPopAlertViewが表示中かどうかを知りたい

`DropPopAlertView`も普通のView(ViewGroup)なので、`View#isVisible`が使えます。  

例：

```kotlin
// 表示中かどうか
val isAlerted = binding.dropPopAlertUpView.isVisible

// もしくは
val isAlerted = binding.dropPopAlertUpView.visibility == View.VISIBLE
```

# ライセンス

```
Copyright 2020 takusan_23

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
    http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
```

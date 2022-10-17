## Configuration 
add implementation 
```kotlin 
implementation("com.github.viroth-ty:base-adapter:$latest_version")
```

add Maven url in build.gradle
```groovy 
pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

#### Note * The BaseAdapter.kt class is extended from ListAdapter
Example ``` MainAdapter.kt ``` with Multiple view

```kotlin
class MainAdapter(val itemClick: (Item) -> Unit) : BaseAdapter<Item, ViewBinding>() {

    override fun inflateView(inflater: LayoutInflater, viewType: Int): ViewBinding {
        if (viewType == 0) {
            return TextItemViewBinding.inflate(inflater)
        } else {
            return NumberItemViewBinding.inflate(inflater)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun bind(binding: ViewBinding, position: Int, item: Item) {
        if (binding is TextItemViewBinding) {
            binding.itemTitle.text = "Text ${item.id}"
        } else if (binding is NumberItemViewBinding) {
            binding.itemTitle.text = "Number ${item.id}"
        }
    }

    override fun onItemClickListener(item: Item) {
        super.onItemClickListener(item)
        itemClick(item)
    }

    override fun itemType(position: Int): Int {
        return if (getItem(position).type == "Number") {
            0
        } else {
            1
        }
    }
}
```

Example ``` MainAdapter.kt ``` without Multiple view

```kotlin
class MainAdapter(val itemClick: (Item) -> Unit) : BaseAdapter<Item, ViewBinding>() {

    override fun inflateView(inflater: LayoutInflater, viewType: Int): ViewBinding {
        return TextItemViewBinding.inflate(inflater)
    }

    @SuppressLint("SetTextI18n")
    override fun bind(binding: ViewBinding, position: Int, item: Item) {
        if (binding is TextItemViewBinding) {
            binding.itemTitle.text = "Text ${item.id}"
        } else if (binding is NumberItemViewBinding) {
            binding.itemTitle.text = "Number ${item.id}"
        }
    }

    override fun onItemClickListener(item: Item) {
        super.onItemClickListener(item)
        itemClick(item)
    }
}
```

In Activity or Fragment
```kotlin
val adapter = MainAdapter() {
    //do something
}
val list = arrayListOf<Item>()
List(10000) {
    list.add(Item(id = it, type = if (it % 2 == 0) "Number" else "Text"))
}
adapter.submitList(list)
binding.recyclerview.adapter = adapter
binding.recyclerview.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


```

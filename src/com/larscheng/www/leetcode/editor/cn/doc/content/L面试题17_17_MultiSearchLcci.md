<p>给定一个较长字符串<code>big</code>和一个包含较短字符串的数组<code>smalls</code>，设计一个方法，根据<code>smalls</code>中的每一个较短字符串，对<code>big</code>进行搜索。输出<code>smalls</code>中的字符串在<code>big</code>里出现的所有位置<code>positions</code>，其中<code>positions[i]</code>为<code>smalls[i]</code>出现的所有位置。</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>
big = "mississippi"
smalls = ["is","ppi","hi","sis","i","ssippi"]
<strong>输出：</strong> [[1,4],[8],[],[3],[1,4,7,10],[5]]
</pre>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>0 &lt;= len(big) &lt;= 1000</code></li> 
 <li><code>0 &lt;= len(smalls[i]) &lt;= 1000</code></li> 
 <li><code>smalls</code>的总字符数不会超过 100000。</li> 
 <li>你可以认为<code>smalls</code>中没有重复字符串。</li> 
 <li>所有出现的字符均为英文小写字母。</li> 
</ul>

<div><div>Related Topics</div><div><li>字典树</li><li>数组</li><li>哈希表</li><li>字符串</li><li>字符串匹配</li><li>滑动窗口</li></div></div><br><div><li>👍 53</li><li>👎 0</li></div>
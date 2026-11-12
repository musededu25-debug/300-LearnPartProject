/**
 * 图书管理页面脚本
 * 你不需要改这个文件，重点是让后端接口符合下面的约定：
 *
 *   GET    /books           → 返回图书数组
 *   POST   /books           → 新增，请求体是 JSON: {name, price}
 *   PUT    /books/{id}      → 修改，请求体是 JSON: {name, price}
 *   DELETE /books/{id}      → 删除
 *
 * 每本书的 JSON 形状：{ "id": 1, "name": "书名", "price": 99 }
 */

const API = '/books';

const listEl = document.getElementById('book-list');
const msgEl = document.getElementById('message');
const form = document.getElementById('book-form');
const idEl = document.getElementById('book-id');
const nameEl = document.getElementById('book-name');
const priceEl = document.getElementById('book-price');
const submitBtn = document.getElementById('submit-btn');
const cancelBtn = document.getElementById('cancel-btn');

/** 统一发请求：支持后端返回 JSON 或纯字符串 */
async function request(url, options = {}) {
    const res = await fetch(url, options);
    const text = await res.text();
    if (!res.ok) {
        throw new Error(`HTTP ${res.status} ${res.statusText}${text ? '：' + text.slice(0, 120) : ''}`);
    }
    if (!text) return null;
    try {
        return JSON.parse(text);
    } catch (e) {
        return text;   // 后端返回的是普通字符串，比如 "book delete"
    }
}

function showMessage(text, isError = false) {
    msgEl.textContent = text;
    msgEl.className = isError ? 'message error' : 'message';
    msgEl.hidden = false;
}

function hideMessage() {
    msgEl.hidden = true;
}

/** 加载列表 */
async function loadBooks() {
    try {
        const books = await request(API);
        if (!Array.isArray(books)) {
            showMessage('接口返回的不是数组，检查 GET /books 是否返回了 List<Book>', true);
            books = [];
        }
        render(books);
    } catch (e) {
        showMessage('加载失败：' + e.message, true);
    }
}

function render(books) {
    listEl.innerHTML = '';
    if (!books || books.length === 0) {
        listEl.innerHTML = '<tr><td colspan="4" class="empty">还没有图书，先添加一本吧</td></tr>';
        return;
    }
    books.forEach(book => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${book.id ?? ''}</td>
            <td>${book.name ?? ''}</td>
            <td>${book.price ?? ''}</td>
            <td>
                <button class="link" data-action="edit">编辑</button>
                <button class="link danger" data-action="delete">删除</button>
            </td>`;
        tr.querySelector('[data-action="edit"]').addEventListener('click', () => startEdit(book));
        tr.querySelector('[data-action="delete"]').addEventListener('click', () => removeBook(book));
        listEl.appendChild(tr);
    });
}

function startEdit(book) {
    idEl.value = book.id;
    nameEl.value = book.name ?? '';
    priceEl.value = book.price ?? '';
    submitBtn.textContent = '保存修改';
    cancelBtn.hidden = false;
    nameEl.focus();
    hideMessage();
}

function resetForm() {
    form.reset();
    idEl.value = '';
    submitBtn.textContent = '添加';
    cancelBtn.hidden = true;
}

/** 添加或修改 */
form.addEventListener('submit', async (e) => {
    e.preventDefault();
    const body = JSON.stringify({
        name: nameEl.value.trim(),
        price: Number(priceEl.value)
    });
    const id = idEl.value;

    try {
        if (id) {
            await request(`${API}/${id}`, {
                method: 'PUT',
                headers: {'Content-Type': 'application/json'},
                body
            });
            showMessage(`已修改 id=${id}`);
        } else {
            await request(API, {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body
            });
            showMessage('已添加');
        }
        resetForm();
        await loadBooks();
    } catch (err) {
        showMessage((id ? '修改' : '添加') + '失败：' + err.message, true);
    }
});

cancelBtn.addEventListener('click', () => {
    resetForm();
    hideMessage();
});

async function removeBook(book) {
    if (!confirm(`确定删除《${book.name}》吗？`)) return;
    try {
        await request(`${API}/${book.id}`, {method: 'DELETE'});
        showMessage(`已删除 id=${book.id}`);
        await loadBooks();
    } catch (e) {
        showMessage('删除失败：' + e.message, true);
    }
}

// 页面打开时加载一次
loadBooks();

import { formatAmount, formatDate } from './utils.js';
const transactionForm = document.getElementById('transaction-form');
const transactionHistoryList = document.getElementById('transaction-history');
const balanceElement = document.getElementById('balance');
const addTransactionButton = document.getElementById('add-transaction-btn');
let transactions = JSON.parse(localStorage.getItem('transactions')) || [];
let balance = 0;
const updateBalance = () => {
  balance = transactions.reduce((acc, transaction) => acc + (transaction.type === 'income' ? transaction.amount : -transaction.amount), 0);
  balanceElement.textContent = `${formatAmount(balance)}`;
};
const renderTransactionHistory = () => {
  transactionHistoryList.innerHTML = '';
  transactions.forEach((transaction) => {
    const listItem = document.createElement('li');
    listItem.textContent = `${formatDate(new Date(transaction.date))} - ${transaction.type.charAt(0).toUpperCase() + transaction.type.slice(1)}: ${formatAmount(transaction.amount)}`;
    transactionHistoryList.appendChild(listItem);
  });
};
const handleAddTransaction = (event) => {
  event.preventDefault();
  const amount = parseFloat(document.getElementById('transaction-amount').value);
  const type = document.getElementById('transaction-type').value;
  if (isNaN(amount) || amount <= 0) return;
  const newTransaction = {
    amount,
    type,
    date: new Date().toISOString(),
  };
  transactions.push(newTransaction);
  localStorage.setItem('transactions', JSON.stringify(transactions));
  updateBalance();
  renderTransactionHistory();
  transactionForm.reset();
};
const handleDeleteTransaction = (index) => {
  transactions.splice(index, 1);
  localStorage.setItem('transactions', JSON.stringify(transactions));
  updateBalance();
  renderTransactionHistory();
};
transactionForm.addEventListener('submit', handleAddTransaction);
updateBalance();
renderTransactionHistory();
transactionHistoryList.addEventListener('click', (event) => {
  if (event.target.tagName === 'LI') {
    const index = Array.prototype.indexOf.call(transactionHistoryList.children, event.target);
    handleDeleteTransaction(index);
  }
});
import { format, isThisYear } from './node_modules/date-fns';
const formatAmount = (amount) => {
  return new Intl.NumberFormat('en-US', {
    style: 'currency',
    currency: 'USD',
  }).format(amount);
};
const formatDate = (date) => {
  return isThisYear(date) ? format(date, 'MMM dd') : format(date, 'MMM dd, yyyy');
};
export { formatAmount, formatDate };
/**
 * 주어진 URL에서 데이터를 비동기적으로 가져옵니다.
 * @param {string} url - 가져올 데이터의 URL
 * @returns {Promise} - 데이터를 담은 Promise 객체
 */
export async function fetchData(url) {
  try {
    const response = await fetch(url);
    const data = await response.json();
    return data;
  } catch (error) {
    console.error('데이터를 가져오는 중 오류 발생:', error);
    return [];
  }
}

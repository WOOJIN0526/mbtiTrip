/**
 * 주어진 데이터 배열에서 주어진 값에 포함된 요소들만 필터링하여 반환합니다.
 * @param {Array} data - 필터링할 데이터 배열
 * @param {string} value - 필터링할 값
 * @returns {Array} - 필터링된 데이터 배열
 */
export function filterData(data, value) {
  return value ? data.filter((label) => label.includes(value)) : [];
}

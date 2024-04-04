console.log("1111");

document.querySelector('.icon-menu').addEventListener('click', function(){
    document.querySelector('.profile').classList.toggle('disActive');
    
});
console.log(document.querySelector('.icon-menu'));
function toggleSVG() {
    var svgElement = document.querySelector('.icon-menu'); // SVG 요소 선택
    var svgPath = svgElement.querySelector('path'); // SVG 내의 path 요소 선택
    
    // 첫 번째 SVG와 두 번째 SVG의 path 데이터를 비교하여 전환
    if (svgPath.getAttribute('d') === "M0 96C0 78.3 14.3 64 32 64H416c17.7 0 32 14.3 32 32s-14.3 32-32 32H32C14.3 128 0 113.7 0 96zM0 256c0-17.7 14.3-32 32-32H416c17.7 0 32 14.3 32 32s-14.3 32-32 32H32c-17.7 0-32-14.3-32-32zM448 416c0 17.7-14.3 32-32 32H32c-17.7 0-32-14.3-32-32s14.3-32 32-32H416c17.7 0 32 14.3 32 32z") {
        svgPath.setAttribute('d', "M0 96C0 78.3 14.3 64 32 64H416c17.7 0 32 14.3 32 32s-14.3 32-32 32H32C14.3 128 0 113.7 0 96zM64 256c0-17.7 14.3-32 32-32H480c17.7 0 32 14.3 32 32s-14.3 32-32 32H96c-17.7 0-32-14.3-32-32zM448 416c0 17.7-14.3 32-32 32H32c-17.7 0-32-14.3-32-32s14.3-32 32-32H416c17.7 0 32 14.3 32 32z");
    } else {
        svgPath.setAttribute('d', "M0 96C0 78.3 14.3 64 32 64H416c17.7 0 32 14.3 32 32s-14.3 32-32 32H32C14.3 128 0 113.7 0 96zM0 256c0-17.7 14.3-32 32-32H416c17.7 0 32 14.3 32 32s-14.3 32-32 32H32c-17.7 0-32-14.3-32-32zM448 416c0 17.7-14.3 32-32 32H32c-17.7 0-32-14.3-32-32s14.3-32 32-32H416c17.7 0 32 14.3 32 32z");
    }
}


// const images = document.querySelectorAll('.image-container .image');
// const rectangles = document.querySelectorAll('.pagination .rectangle');

// images.forEach((image, index) => {
//     image.addEventListener('transitionend', () => {
//         rectangles.forEach((rectangle, rectangleIndex) => {
//             rectangle.classList.remove('active');
//             if (index === rectangleIndex) {
//                 rectangle.classList.add('active');
//             }
//         });
//     });
// });
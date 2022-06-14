let searchForm = document.querySelector('.search-form');

document.querySelector('#search-btn').onclick = () =>{
  searchForm.classList.toggle('active');
  navbar.classList.remove('active');
}

let navbar = document.querySelector('.navbar');

document.querySelector('#menu-btn').onclick = () =>{
  navbar.classList.toggle('active');
  searchForm.classList.remove('active');
}

window.onscroll = () =>{
  searchForm.classList.remove('active');
  navbar.classList.remove('active');
}

let filterBtn = document.querySelectorAll('.filter-buttons .buttons');
let filterItem = document.querySelectorAll('.products .box-container .box');

filterBtn.forEach(button =>{

  button.onclick = () =>{
    filterBtn.forEach(btn => btn.classList.remove('active'));
    button.classList.add('active');

    let dataFilter = button.getAttribute('data-filter');

    filterItem.forEach(item =>{

      item.classList.remove('active');
      item.classList.add('hide');

      if(item.getAttribute('data-item') == dataFilter || dataFilter == 'all'){
        item.classList.remove('hide');
        item.classList.add('active');
      }

    });

  };

});

var swiper = new Swiper(".home-slider", {
  centeredSlides: true,
  loop:true,
  autoplay: {
    delay: 9500,
    disableOnInteraction: false,
  },
  navigation: {
    nextEl: ".swiper-button-next",
    prevEl: ".swiper-button-prev",
  },
});

var swiper = new Swiper(".featured-slider", {
  centeredSlides: true,
  loop:true,
  spaceBetween:20,
  autoplay: {
    delay: 9500,
    disableOnInteraction: false,
  },
  navigation: {
    nextEl: ".swiper-button-next",
    prevEl: ".swiper-button-prev",
  },
  breakpoints: {
    0: {
      slidesPerView: 1,
    },
    450: {
      slidesPerView: 2,
    },
    768: {
      slidesPerView: 3,
    },
    1200: {
      slidesPerView: 4,
    },
  },
});

var swiper = new Swiper(".review-slide", {
  loop:true,
  spaceBetween:20,
  autoplay: {
    delay: 9500,
    disableOnInteraction: false,
  },
  breakpoints: {
    0: {
      slidesPerView: 1,
    },
    768: {
      slidesPerView: 2,
    },
  },
});

var swiper = new Swiper(".blogs-slider", {
  centeredSlides: true,
  loop:true,
  spaceBetween:20,
  autoplay: {
    delay: 9500,
    disableOnInteraction: false,
  },
  navigation: {
    nextEl: ".swiper-button-next",
    prevEl: ".swiper-button-prev",
  },
  breakpoints: {
    0: {
      slidesPerView: 1,
    },
    768: {
      slidesPerView: 2,
    },
    1200: {
      slidesPerView: 3,
    },
  },
});
// Cart
var inputDetails = document.getElementById('qtyProduct');
if(inputDetails != null) {
	inputDetails.onchange = function() {
		inputDetails.setAttribute('value', inputDetails.value);
	}
}
var getCard = document.getElementById('cart')
if(getCard != null) {
    var getInputs = getCard.querySelectorAll('input[name=qty]');
    getInputs.forEach(function(input) {
        var getPrevTd = input.parentElement.previousElementSibling;
        var getNextTd = input.parentElement.nextElementSibling;
        var getPrice = getPrevTd.innerHTML.match(/\d/g);
        getPrice = getPrice.join('');
        var totalPrice = input.value * getPrice;
        getNextTd.innerHTML = totalPrice.toLocaleString('vi-VN') + ' VNĐ';
    })
    var getTotalParent = document.getElementById('cart-add');
    if(getTotalParent!=null) {
        var getTotalElement = getTotalParent.querySelector('.total');
        // Mặc định sẽ tính tổng
        var total = 0;
        getInputs.forEach(function(input) {
            var getNextTd = input.parentElement.nextElementSibling;
            var getTotalPriceProduct = getNextTd.innerHTML.match(/\d/g);
            getTotalPriceProduct = getTotalPriceProduct.join('');
            total+=Number(getTotalPriceProduct);
        })
        getTotalElement.innerHTML = total.toLocaleString('vi-VN') + ' VNĐ';
    }
    getInputs.forEach( function(input) {
        input.onchange = function() {
            var getPrevTd = input.parentElement.previousElementSibling;
            var getPriceProduct = getPrevTd.innerHTML.match(/\d/g);
            getPriceProduct = getPriceProduct.join('');
            // Update value for Input
            input.setAttribute('value', input.value);
            // Total Price
            var getNextTd = input.parentElement.nextElementSibling;
            // var getTotalPrice = getNextTd.innerHTML.match(/\d/g);
            // getTotalPrice = getTotalPrice.join('');
            var qty = input.value;
            var totalPrice = getPriceProduct * qty;
            getNextTd.innerHTML = totalPrice.toLocaleString('vi-VN') + ' VNĐ';
            // Tổng tiền tất cả các sản phẩm
            var total = 0;
            getInputs.forEach(function(input) {
                var getNextTd = input.parentElement.nextElementSibling;
                var getTotalPriceProduct = getNextTd.innerHTML.match(/\d/g);
                getTotalPriceProduct = getTotalPriceProduct.join('');
                total+=Number(getTotalPriceProduct);
            })
            var getTotalElement = getTotalParent.querySelector('.total');
            getTotalElement.innerHTML = total.toLocaleString('vi-VN') + ' VNĐ';
        }
    })

}
// Sort table
var getTable = document.getElementById('myTable')
if(getTable != null) {
    var getHeading = document.getElementById('heading')
    var getRow = getHeading.getElementsByTagName('th');
    var convertArr = Array.from(getRow);
    convertArr.forEach(function(element) {
        element.onclick = function() {
            var order = $(this).data('order');
            var iconSort = element.firstElementChild;
                if(order == 'desc') {
                    iconSort.classList.remove('fa-sort-alpha-up')
                    iconSort.classList.add('fa-sort-alpha-down')
                    $(this).data('order', 'asc');
                } else {
                    iconSort.classList.remove('fa-sort-alpha-down')
                    iconSort.classList.add('fa-sort-alpha-up')
                    $(this).data('order', 'desc');
                }
        }
    })
}
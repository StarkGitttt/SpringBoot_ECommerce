<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<!DOCTYPE html>
<html lang>
<head>
<meta charset="UTF-8">
<title>Fashion Shop</title>
</head>
<body>
<!-- banner section starts  -->
 <section id="feature" class="section-p1">
        <div class="fe-box">
           <img src="/assets/images/features/f1.png" alt="">
           <h6>Free Shipping</h6>
        </div>
        <div class="fe-box">
            <img src="/assets/images/features/f2.png" alt="">
            <h6>Online Order</h6>
        </div>
        <div class="fe-box">
            <img src="/assets/images/features/f3.png" alt="">
            <h6>Save Money</h6>
        </div>
        <div class="fe-box">
            <img src="/assets/images/features/f4.png" alt="">
            <h6>Promotions</h6>
        </div>
        <div class="fe-box">
            <img src="/assets/images/features/f5.png" alt="">
            <h6>Happy Sell</h6>
        </div>
        <div class="fe-box">
            <img src="/assets/images/features/f6.png" alt="">
            <h6>F24/7 Support</h6>
        </div>
  </section>
   
<section class="banner-container">

    <div class="banner">
        <img src="/assets/images/shop_banner_img1.jpg" alt="">
        <div class="content">
            <span>Đặc biệt</span>
            <h3>Giảm 50%</h3>
            <a href="#" class="btn-default">Chi tiết</a>
        </div>
    </div>
    
    <div class="banner">
        <img src="/assets/images/shop_banner_img2.jpg" alt="">
        <div class="content">
            <span>Đặc biệt</span>
            <h3>Giảm 50%</h3>
            <a href="#" class="btn-default">Chi tiết</a>
        </div>
    </div>

</section>

   
<!-- banner section ends -->

<!-- featured section starts  -->
<c:if test="${reports.size() >= 10}">
	
	<section class="featured" id="featured">
	
	    <h1 class="heading"><span>Sản phẩm</span> nổi bật</h1>
	
	    <div class="swiper featured-slider">
	        
	        <div class="swiper-wrapper">
	
	    		<c:forEach var="report" items="${reports}">
		            <div class="swiper-slide slide">
		                <div class="icons">
		                    <a href="/cart/add?id=${report.group.id}" class="fas fa-shopping-cart"></a>
		                    <c:set var="isLike" value="false" scope="page"></c:set>
		               		<c:if test="${sessionScope.user != null }">
		               			<c:forEach var="favorite" items="${favorites}">
		               				<c:if test="${favorite.product.id == report.group.id }">
		               						<c:set var="isLike" value="true" scope="page"></c:set>
		               				</c:if>
		               			</c:forEach>
		               		</c:if>
		                    <a href="/product/like?id=${report.group.id}&page=index" class="fas fa-heart ${isLike ? 'isLike' : ''}"></a>                  
		                    <a href="/product/details?id=${report.group.id}" class="fas fa-eye"></a>
		                </div>
		                <div class="image">
		                    <img src="${report.group.image}" alt="">
		                </div>
		                <div class="content">
		                    <h3>${report.group.name }</h3>
		                    <div class="price">
		                    	<c:choose>
		                    		<c:when test="${report.group.discount <= 0 }">
				                        <div class="amount">
				                        	<fmt:formatNumber value="${report.group.price}" pattern="#,###" /> VNĐ
				                        </div>	                    		
		                    		</c:when>
		                    		<c:when test="${newProduct.discount > 0 }">
					                     <div class="cut"><fmt:formatNumber value="${report.group.price}" pattern="#,###" /></div>
				                         <div class="offer">
				                         	<fmt:formatNumber value="${report.group.price * ((100 - report.group.discount) / 100)}" pattern="#,###" /> VNĐ
				                         </div>	                    			
		                    		</c:when>
		                    	</c:choose>
		                       <!--  <div class="cut">$25.00</div>
		                        <div class="offer">20% off</div>  -->
		                    </div>
		                    <div class="stars">
		                        <i class="fas fa-star"></i>
		                        <i class="fas fa-star"></i>
		                        <i class="fas fa-star"></i>
		                        <i class="fas fa-star"></i>
		                        <i class="far fa-star"></i>
		                        <span>(50)</span>
		                    </div>
		                </div>
	            	</div>		
			</c:forEach>
	            
	        </div>
	
	        <div class="swiper-button-next"></div>
	        <div class="swiper-button-prev"></div>
	
	    </div>
	
	</section>
	
</c:if>

<!-- featured section ends -->
<!-- deal section starts  -->

<section class="deal">

    <div class="image">
        <img src="/assets/images/tranding_img.png" alt="">
    </div>

    <div class="content">
        <span>Xu hướng theo mùa!</span>
        <h3>Bộ sưu tập mùa hè</h3>
        <p>Giảm giá lên đến 50%</p>
        <a href="#" class="btn-default">Chi tiết</a>
    </div>

</section>

<!-- deal section ends -->




<!-- new product  section starts  -->

<section class="featured" id="featured">

    <h1 class="heading"><span>Sản phẩm</span> mới</h1>

    <div class="swiper featured-slider">
        
        <div class="swiper-wrapper">
			<c:forEach var="newProduct" items="${newProducts }">
	            <div class="swiper-slide slide">
	                <div class="icons">
	                    <a href="/cart/add?id=${newProduct.id}" class="fas fa-shopping-cart"></a>
	                    <c:set var="isLike" value="false" scope="page"></c:set>
	               		<c:if test="${sessionScope.user != null }">
	               			<c:forEach var="favorite" items="${favorites}">
	               				<c:if test="${favorite.product.id == newProduct.id }">
	               						<c:set var="isLike" value="true" scope="page"></c:set>
	               				</c:if>
	               			</c:forEach>
	               		</c:if>
	                    <a href="/product/like?id=${newProduct.id}&page=index" class="fas fa-heart ${isLike ? 'isLike' : ''}"></a>                  
	                    <a href="/product/details?id=${newProduct.id}" class="fas fa-eye"></a>
	                </div>
	                <div class="image">
	                    <img src="${newProduct.image }" alt="">
	                </div>
	                <div class="content">
	                    <h3>${newProduct.name }</h3>
	                    <div class="price">
	                    	<c:choose>
	                    		<c:when test="${newProduct.discount <= 0 }">
			                        <div class="amount">
			                        	<fmt:formatNumber value="${newProduct.price}" pattern="#,###" /> VNĐ
			                        </div>	                    		
	                    		</c:when>
	                    		<c:when test="${newProduct.discount > 0 }">
				                     <div class="cut"><fmt:formatNumber value="${newProduct.price}" pattern="#,###" /></div>
			                         <div class="offer">
			                         	<fmt:formatNumber value="${newProduct.price * ((100 - newProduct.discount) / 100)}" pattern="#,###" /> VNĐ
			                         </div>	                    			
	                    		</c:when>
	                    	</c:choose>
	                       <!--  <div class="cut">$25.00</div>
	                        <div class="offer">20% off</div>  -->
	                    </div>
	                    <div class="stars">
	                        <i class="fas fa-star"></i>
	                        <i class="fas fa-star"></i>
	                        <i class="fas fa-star"></i>
	                        <i class="fas fa-star"></i>
	                        <i class="far fa-star"></i>
	                        <span>(50)</span>
	                    </div>
	                </div>
	            </div>		
			</c:forEach>
        </div>
        
        <div class="swiper-button-next"></div>
        <div class="swiper-button-prev"></div>

    </div>

</section>

<!-- featured section ends -->

<!-- reviews section starts  -->

<section class="review" id="review">

    <h1 class="heading"> Đánh giá của <span>khách hàng</span> </h1>

    <div class="swiper review-slide">

        <div class="swiper-wrapper">

            <div class="swiper-slide slide">
                <p>Nhãn mác quần áo đẹp có ý nghĩa quan trọng. Không chỉ với các thương hiệu may mặc mà còn cả đối với khách hàng.</p>
                <div class="user">
                    <img src="/assets/images/pic-1.png" alt="">
                    <div class="info">
                        <h3>john deo</h3>
                        <span>happy client</span>
                    </div>
                </div>
            </div>

            <div class="swiper-slide slide">
                <p>Nhãn mác quần áo đẹp có ý nghĩa quan trọng. Không chỉ với các thương hiệu may mặc mà còn cả đối với khách hàng.</p>
                <div class="user">
                    <img src="/assets/images/pic-2.png" alt="">
                    <div class="info">
                        <h3>john deo</h3>
                        <span>happy client</span>
                    </div>
                </div>
            </div>

            <div class="swiper-slide slide">
                <p>Nhãn mác quần áo đẹp có ý nghĩa quan trọng. Không chỉ với các thương hiệu may mặc mà còn cả đối với khách hàng.</p>
                <div class="user">
                    <img src="/assets/images/pic-3.png" alt="">
                    <div class="info">
                        <h3>john deo</h3>
                        <span>happy client</span>
                    </div>
                </div>
            </div>

            <div class="swiper-slide slide">
                <p>Nhãn mác quần áo đẹp có ý nghĩa quan trọng. Không chỉ với các thương hiệu may mặc mà còn cả đối với khách hàng.</p>
                <div class="user">
                    <img src="/assets/images/pic-4.png" alt="">
                    <div class="info">
                        <h3>john deo</h3>
                        <span>happy client</span>
                    </div>
                </div>
            </div>

            <div class="swiper-slide slide">
                <p>Nhãn mác quần áo đẹp có ý nghĩa quan trọng. Không chỉ với các thương hiệu may mặc mà còn cả đối với khách hàng.</p>
                <div class="user">
                    <img src="/assets/images/pic-5.png" alt="">
                    <div class="info">
                        <h3>john deo</h3>
                        <span>happy client</span>
                    </div>
                </div>
            </div>

            <div class="swiper-slide slide">
                <p>Nhãn mác quần áo đẹp có ý nghĩa quan trọng. Không chỉ với các thương hiệu may mặc mà còn cả đối với khách hàng.</p>
                <div class="user">
                    <img src="/assets/images/pic-6.png" alt="">
                    <div class="info">
                        <h3>john deo</h3>
                        <span>happy client</span>
                    </div>
                </div>
            </div>

        </div>

    </div>

</section>

<!-- reviews section ends -->

<!-- contact section starts  -->

<section class="contact" id="contact">

    <h1 class="heading"> <span>Liên hệ</span> với chúng tôi</h1>

    <div class="icons-container">

        <div class="icons">
            <i class="fas fa-map-marker-alt"></i>
            <h3>Địa chỉ</h3>
            <p>K364 Lê Duẩn, TP.Đà Nẵng</p>
            <p>147 Nguyễn Thị Thập, TP.Đà Nẵng</p>
        </div>

        <div class="icons">
            <i class="fas fa-envelope"></i>
            <h3>Email</h3>
            <p>lcphan512@gmail.com</p>
            <p>locptpd04185@fpt.edu.vn</p>
        </div>

        <div class="icons">
            <i class="fas fa-phone"></i>
            <h3>Điện thoại</h3>
            <p>+84935071434</p>
            <p>+84246469081</p>
        </div>

    </div>

    <div class="row">

        <form action="">
            <h3>get in touch</h3>
            <div class="inputBox">
                <input type="text" placeholder="your name">
                <input type="email" placeholder="your email">
            </div>
            <div class="inputBox">
                <input type="number" placeholder="your number">
                <input type="text" placeholder="your subject">
            </div>
            <textarea placeholder="your message" cols="30" rows="10"></textarea>
            <input type="submit" value="send message" class="btn-default">
        </form>
        <iframe class="map" src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3833.93757042559!2d108.2083641!3d16.0687291!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3142184b87da6c6d%3A0x114500026389bb54!2zSyAzNjQgTMOqIER14bqpbiwgVMOibiBDaMOtbmgsIFRoYW5oIEtow6osIMSQw6AgTuG6tW5nIDU1MDAwMA!5e0!3m2!1svi!2s!4v1653061174999!5m2!1svi!2s"allowfullscreen="" loading="lazy" ></iframe>
    </div>

</section>

<!-- contact section ends -->
</body>
</html>
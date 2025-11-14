<#macro template user>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title  -->
    <title>Amado - Мебель и декор</title>

    <!-- Favicon  -->
    <link rel="icon" href="/static/img/core-img/favicon.ico">

    <!-- Core Style CSS -->
    <link rel="stylesheet" href="/static/css/core-style.css">

<!--    JQuery-->
    <script src="https://snipp.ru/cdn/jquery/2.1.1/jquery.min.js"></script>

</head>

<body>
<!-- Search Wrapper Area Start -->
<div class="search-wrapper section-padding-100">
    <div class="search-close">
        <i class="fa fa-close" aria-hidden="true"></i>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="search-content">
                    <form action="/" method="get">
                        <input type="search" name="title" id="search" placeholder="Введите ключевое слово...">
                        <button type="submit"><img src="/static/img/core-img/search.png" alt=""></button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Search Wrapper Area End -->

<!-- ##### Main Content Wrapper Start ##### -->
<div class="main-content-wrapper d-flex clearfix">

    <!-- Mobile Nav (max width 767px)-->
    <div class="mobile-nav">
        <!-- Navbar Brand -->
        <div class="amado-navbar-brand">
            <a href="/"><img src="/static/img/core-img/logo.png" alt=""></a>
        </div>
        <!-- Navbar Toggler -->
        <div class="amado-navbar-toggler">
            <span></span><span></span><span></span>
        </div>
    </div>

    <!-- Header Area Start -->
    <header class="header-area clearfix">
        <!-- Close Icon -->
        <div class="nav-close">
            <i class="fa fa-close" aria-hidden="true"></i>
        </div>
        <!-- Logo -->
        <div class="logo">
            <a href="/"><img src="/static/img/core-img/logo.png" alt="" title="Мебель и декор"></a>
        </div>





<!-- Блок авторизации-->
        <div class="login">
            <#if user.email??>

            <h2 class="h2_title">Добро пожаловать, <span class="h2_title__name">${user.name}</span>!</h2>
            <form action="/logout" method="post">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <input type="submit" value="Выйти" class="btn amado-btn1">
            </form>

            <#else>
            <h2 class="h2_title h2_title__name">Авторизация</h2>
            <p>
                <a href="/login" class="p_title">Войти</a> |
                <a href="/registration" class="p_title">Регистрация</a>
            </p>
        </#if>
</div>





        <!-- Amado Nav -->
        <nav class="amado-nav">
            <ul>
                <li class="active"><a href="/">Главная</a></li>
                <li><a target="_blank" href="https://themewagon.com/themes/free-html5-e-commerce-template-bootstrap4-amado/">О компании</a></li>
                <li><a href="/me/products">Магазин</a></li>
                <li><a href="/cart">Корзина</a></li>
            </ul>
        </nav>
        <!-- Button Group -->
        <div class="amado-btn-group mt-30 mb-100">
            <a href="/#" class="btn amado-btn mb-15">%Скидка%</a>
            <a href="/product/new" class="btn amado-btn active">Новое за сутки</a>
        </div>
        <!-- Cart Menu -->
        <div class="cart-fav-search mb-100">
            <a href="/cart" class="cart-nav">
                <img src="/static/img/core-img/cart.png" alt=""> Корзина <span>${amount_of_products_in_cart!0}</span>
                <a href="/static/#" class="fav-nav"><img src="/static/img/core-img/favorites.png" alt=""> Избранное</a>
            <a href="#" class="search-nav"><img src="/static/img/core-img/search.png" alt=""> Поиск</a>
        </div>
        <!-- Social Button -->
        <div class="social-info d-flex justify-content-between">
            <a href="/static/#"><i class="fa fa-pinterest" aria-hidden="true"></i></a>
            <a href="/static/#"><i class="fa fa-instagram" aria-hidden="true"></i></a>
            <a href="/static/#"><i class="fa fa-facebook" aria-hidden="true"></i></a>
            <a href="/static/#"><i class="fa fa-twitter" aria-hidden="true"></i></a>
        </div>
    </header>
    <!-- Header Area End -->




    <!-- Изменяемое содержимое здесь -->
    <#nested/>



</div>
<!-- ##### Main Content Wrapper End ##### -->



<#if user.email??>

<div class="col-12 section_form">

  <section class="checkout_details_area mt-50 clearfix">

 <div class="cart-title">
<h2>Добавить товар</h2>
</div>

<!--Форма для создания нового товара-->
<form action="/product/create" method="post" enctype="multipart/form-data"
>
    <div class="row">
    <div class="col-12 mb-3">
    <input type="text" class="form-control" name="title" placeholder="Название товара" required
    >
    </div>

        <div class="col-12 mb-3">
    <textarea name="description" cols="30" rows="10" class="form-control w-100"
                               placeholder="Опишите Ваш товар" required></textarea>
        </div>

        <div class="col-12 mb-3">
        <input type="number" class="form-control" name="price" placeholder="Сколько стоит?"
                         required>
        </div>

        <div class="col-12 mb-3">
    <input type="text" class="form-control" name="city" placeholder="Из какого Вы
города?" required>
        </div>


        <div class="col-12 mb-3">
            <label class="input-file">
                <input type="file" name="file1" id="f1" required>
                <span class="input-file-btn">Выберите файл</span>
                <span class="input-file-text">Фотография 1</span>
            </label>

            <script>
                $('#f1').on('change', function() {
                let file = this.files[0];
    $(this).closest('.input-file').find('.input-file-text').html(file.name);
                });
            </script>
        </div>

        <div class="col-12 mb-3">
            <label class="input-file">
                <input type="file" name="file2" id="f2">
                <span class="input-file-btn">Выберите файл</span>
                <span class="input-file-text">Фотография 2</span>
            </label>

            <script>
                $('#f2').on('change', function() {
                let file = this.files[0];
    $(this).closest('.input-file').find('.input-file-text').html(file.name);
                });
            </script>
        </div>


        <div class="col-12 mb-3">
            <label class="input-file">
                <input type="file" name="file3" id="f3">
                <span class="input-file-btn">Выберите файл</span>
                <span class="input-file-text">Фотография 3</span>
            </label>

            <script>
                $('#f3').on('change', function() {
                let file = this.files[0];
    $(this).closest('.input-file').find('.input-file-text').html(file.name);
                });
            </script>
        </div>



        <div class="col-12 mb-3">
            <label class="input-file">
                <input type="file" name="file4" id="f4">
                <span class="input-file-btn">Выберите файл</span>
                <span class="input-file-text">Фотография 4</span>
            </label>

            <script>
                $('#f4').on('change', function() {
                let file = this.files[0];
    $(this).closest('.input-file').find('.input-file-text').html(file.name);
                });
            </script>
        </div>


    <input type="hidden" name="_csrf" value="${_csrf.token}">

       <div class="form_btn">
    <input class="btn amado-btn2" type="submit" value="Добавить товар">
       </div>

</div>
</form>
  </section>
</div>
</#if>







<!-- ##### Newsletter Area Start ##### -->
<section class="newsletter-area section-padding-100-0">
    <div class="container">
        <div class="row align-items-center">
            <!-- Newsletter Text -->
            <div class="col-12 col-lg-6 col-xl-7">
                <div class="newsletter-text mb-100">
                    <h2>Подпишитесь для <span>25% Скидки</span></h2>
                    <p>В ходе акции скидки предоставляются на товары с розничной ценой свыше 5 рублей, если иное не определено дополнительными условиями.</p>
                </div>
            </div>
            <!-- Newsletter Form -->
            <div class="col-12 col-lg-6 col-xl-5">
                <div class="newsletter-form mb-100">
                    <form action="#" method="post">
                        <input type="email" name="email" class="nl-email" placeholder="Ваш E-mail">
                        <input type="submit" value="Подписаться">
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- ##### Newsletter Area End ##### -->

<!-- ##### Footer Area Start ##### -->
<footer class="footer_area clearfix">
    <div class="container">
        <div class="row align-items-center">
            <!-- Single Widget Area -->
            <div class="col-12 col-lg-4">
                <div class="single_widget_area">
                    <!-- Logo -->
                    <div class="footer-logo mr-50">
                        <a href="/"><img src="/static/img/core-img/logo2.png" alt=""></a>
                    </div>
                    <!-- Copywrite Text -->
                    <p class="copywrite"><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                        Copyright &copy;<script>document.write(new Date().getFullYear());</script> Все права защищены | Этот шаблон создан в <i class="fa fa-heart-o" aria-hidden="true"></i> <a href="/static/https://colorlib.com" target="_blank">Colorlib</a>
                        <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>
                </div>
            </div>
            <!-- Single Widget Area -->
            <div class="col-12 col-lg-8">
                <div class="single_widget_area">
                    <!-- Footer Menu -->
                    <div class="footer_menu">
                        <nav class="navbar navbar-expand-lg justify-content-end">
                            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#footerNavContent" aria-controls="footerNavContent" aria-expanded="false" aria-label="Toggle navigation"><i class="fa fa-bars"></i></button>
                            <div class="collapse navbar-collapse" id="footerNavContent">
                                <ul class="navbar-nav ml-auto">
                                    <li class="nav-item active">
                                        <a class="nav-link" href="/">Главная</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="/static/#">О компании</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="/static/#">Магазин</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="/static/#">Корзина</a>
                                    </li>
                                </ul>
                            </div>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
    </div>
</footer>
<!-- ##### Footer Area End ##### -->

<!-- ##### jQuery (Necessary for All JavaScript Plugins) ##### -->
<script src="/static/js/jquery/jquery-2.2.4.min.js"></script>
<!-- Popper js -->
<script src="/static/js/popper.min.js"></script>
<!-- Bootstrap js -->
<script src="/static/js/bootstrap.min.js"></script>
<!-- Plugins js -->
<script src="/static/js/plugins.js"></script>
<!-- Active js -->
<script src="/static/js/active.js"></script>

</body>

</html>
</#macro>
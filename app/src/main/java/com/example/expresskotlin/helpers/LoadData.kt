package com.example.expresskotlin.helpers

import com.example.expresskotlin.models.*

class LoadData {





    companion object{



        fun getAllMenuCategoriaList():ArrayList<MenuCategoria>{
            var menuCategoriaList = ArrayList<MenuCategoria>()

            menuCategoriaList.add(MenuCategoria(1,"Hamburgarias",getAllEstabBurguersList()))
            menuCategoriaList.add(MenuCategoria(2,"Pizzarias",getAllEstabPizzaList()))
            menuCategoriaList.add(MenuCategoria(3,"Churrascarias", getAllEstabChurrascoList()))
            return menuCategoriaList
        }

        //===============================ALL BURGERS_ESTAB=================================================
        //============================================================================================
        fun getAllEstabBurguersList():ArrayList<Estabelecimento>{
            var menuEstabelecimentoList = ArrayList<Estabelecimento>()

            menuEstabelecimentoList.add(Estabelecimento(1,"Crazy Burges","Restaurante",
                "3,4 Km",getAllMenuCatProdutosBurgersList(),
                "https://www.tastingtable.com/img/gallery/what-makes-restaurant-burgers-taste-different-from-homemade-burgers/l-intro-1650118593.jpg"))
            menuEstabelecimentoList.add(Estabelecimento(2,"Hamburguer City","Lanchonete",
                "4,1 Km",getAllMenuCatProdutosBurgersList(),
                "https://cdn.winsightmedia.com/platform/files/public/600x450/hamburger-trio.jpg"))

            menuEstabelecimentoList.add(Estabelecimento(3,"Dooh Ponto","Lanchonete",
                "5,4 Km",getAllMenuCatProdutosBurgersList(),
                "https://static.wixstatic.com/media/6b1642_652993627833421d88dcba6e632b7882~mv2.png"))

            menuEstabelecimentoList.add(Estabelecimento(4,"Simply Burgers","Restaurante",
                "7,4 Km",getAllMenuCatProdutosBurgersList(),
                "https://ro-images-reserveout.netdna-ssl.com/cdn/0x0/1635939370183026831.jpg"))

            menuEstabelecimentoList.add(Estabelecimento(5,"Deliburgers","Restaurante",
                "8,4 Km",getAllMenuCatProdutosBurgersList(),
                "https://www.wallpaperup.com/uploads/wallpapers/2018/08/26/1288746/40018918f79a01f4bbe048be01916ca4-700.jpg"))

            menuEstabelecimentoList.add(Estabelecimento(6,"Oh! Burgers","Restaurante",
                "13,4 Km",getAllMenuCatProdutosBurgersList(),
                "https://receitinhas.com.br/wp-content/uploads/2017/02/Hamburguer-com-cheddar-e-r%C3%BAcula-1200x800.jpg"))

            menuEstabelecimentoList.add(Estabelecimento(7,"Burgerlicious","Restaurante",
                "16,4 Km",getAllMenuCatProdutosBurgersList(),
                "https://img.itdg.com.br/tdg/images/recipes/000/161/709/342919/342919_original.jpg?w=1200"))

            return menuEstabelecimentoList
        }

        fun getAllMenuCatProdutosBurgersList():ArrayList<MenuCatProdutos>{
            var menuMenuCatProdutosList = ArrayList<MenuCatProdutos>()



            menuMenuCatProdutosList.add(MenuCatProdutos(1,"Hamburgers", getALLBurgersList()))
            menuMenuCatProdutosList.add(MenuCatProdutos(2,"Refrigerantes", getALLRefrigeranteList()))


            return menuMenuCatProdutosList
        }


        //===============================ALL PIZZA_ESTAB=================================================
        //============================================================================================
        fun getAllEstabPizzaList():ArrayList<Estabelecimento>{
            var menuEstabelecimentoList = ArrayList<Estabelecimento>()

            menuEstabelecimentoList.add(Estabelecimento(1,"Bella Pizza","Restaurante",
                "3,4 Km",getAllMenuCatProdutosPizzaList(),
                "https://media.gazetadopovo.com.br/2021/07/09163516/receita-massa-pizza-bigstock-960x540.jpg"))
            menuEstabelecimentoList.add(Estabelecimento(2,"Pizzarella","Lanchonete",
                "4,1 Km",getAllMenuCatProdutosPizzaList(),
                "https://st.depositphotos.com/1003814/5052/i/950/depositphotos_50523105-stock-photo-pizza-with-tomatoes.jpg"))

            menuEstabelecimentoList.add(Estabelecimento(3,"Pizza Hut","Lanchonete",
                "5,4 Km",getAllMenuCatProdutosPizzaList(),
                "https://hotmart.s3.amazonaws.com/product_contents/62f2be78-75cb-49cf-bae4-bb6fbb76faec/depositphotos_41466555stockphotoimageofsliceofpizza.jpg"))

            menuEstabelecimentoList.add(Estabelecimento(4,"Simply Pizza","Restaurante",
                "7,4 Km",getAllMenuCatProdutosPizzaList(),
                "https://images.deliveryhero.io/image/fd-pk/LH/q6lz-hero.jpg"))

            menuEstabelecimentoList.add(Estabelecimento(5,"Pizza!","Restaurante",
                "8,4 Km",getAllMenuCatProdutosPizzaList(),
                "https://static.toiimg.com/photo/msid-87930581/87930581.jpg?211826"))

            menuEstabelecimentoList.add(Estabelecimento(6,"Italizza","Restaurante",
                "13,4 Km",getAllMenuCatProdutosPizzaList(),
                "https://pbs.twimg.com/media/FTNbp3WXEAAIZcb.jpg"))

            menuEstabelecimentoList.add(Estabelecimento(7,"Juzzto!","Restaurante",
                "16,4 Km",getAllMenuCatProdutosPizzaList(),
                "https://pizzaolive.in/wp-content/uploads/2021/04/food-pizza-wallpaper-preview.jpg"))

            return menuEstabelecimentoList
        }

        fun getAllMenuCatProdutosPizzaList():ArrayList<MenuCatProdutos>{
            var menuMenuCatProdutosList = ArrayList<MenuCatProdutos>()



            menuMenuCatProdutosList.add(MenuCatProdutos(1,"Pizzas", getALLPizzaList()))
            menuMenuCatProdutosList.add(MenuCatProdutos(2,"Gelados", getALLGeladoList()))



            return menuMenuCatProdutosList
        }

        //===============================ALL CHURRASCO_ESTAB=================================================
        //============================================================================================
        fun getAllEstabChurrascoList():ArrayList<Estabelecimento>{
            var menuEstabelecimentoList = ArrayList<Estabelecimento>()

            menuEstabelecimentoList.add(Estabelecimento(1,"Rasca","Restaurante",
                "3,4 Km",getAllMenuCatProdutosChurrascoList(),
                "https://previews.123rf.com/images/alexraths/alexraths1505/alexraths150500126/40695099-assorted-delicious-grilled-meat-with-vegetable-over-the-coals-on-a-barbecue.jpg"))
            menuEstabelecimentoList.add(Estabelecimento(2,"Churraskah!","Lanchonete",
                "4,1 Km",getAllMenuCatProdutosChurrascoList(),
                "https://static3.bigstockphoto.com/1/1/9/large1500/91112675.jpg"))

            menuEstabelecimentoList.add(Estabelecimento(3,"BarbecueZ","Lanchonete",
                "5,4 Km",getAllMenuCatProdutosChurrascoList(),
                "https://c8.alamy.com/comp/2C650XM/assorted-delicious-grilled-meat-with-vegetables-over-the-coals-on-a-barbecue-2C650XM.jpg"))

            menuEstabelecimentoList.add(Estabelecimento(4,"Yakitori","Restaurante",
                "7,4 Km",getAllMenuCatProdutosChurrascoList(),
                "https://media.istockphoto.com/photos/assorted-delicious-grilled-meat-on-a-barbecue-picture-id673139370"))

            menuEstabelecimentoList.add(Estabelecimento(5,"Chill Grillz","Restaurante",
                "8,4 Km",getAllMenuCatProdutosChurrascoList(),
                "https://ultragazempresas.com.br/wp-content/uploads/2018/05/post_churrascarias_carne_carvao_tao_gostosa_gas.png"))

            menuEstabelecimentoList.add(Estabelecimento(6,"Juscken","Restaurante",
                "13,4 Km",getAllMenuCatProdutosChurrascoList(),
                "https://super.abril.com.br/wp-content/uploads/2018/10/churrasco.png"))

            menuEstabelecimentoList.add(Estabelecimento(7,"Chicky Chicky","Restaurante",
                "16,4 Km",getAllMenuCatProdutosChurrascoList(),
                "https://media.istockphoto.com/photos/rib-eye-steaks-with-corn-and-veggie-kabobs-picture-id592676962"))

            return menuEstabelecimentoList
        }

        fun getAllMenuCatProdutosChurrascoList():ArrayList<MenuCatProdutos>{
            var menuMenuCatProdutosList = ArrayList<MenuCatProdutos>()



            menuMenuCatProdutosList.add(MenuCatProdutos(1,"Churrascos", getALLChurrascoList()))
            menuMenuCatProdutosList.add(MenuCatProdutos(2,"Cervejas", getALLCervejaList()))



            return menuMenuCatProdutosList
        }



        //===============================ALL PRODUCTS=================================================
        //============================================================================================
        fun getAllProdutos():ArrayList<Produtos>{
            var produtosList = ArrayList<Produtos>()

            //===============================HAMBURGERS=================================================
            produtosList.add(Produtos(1,"Mad Burger",4500.00,"burguer",
                    "https://thumbs.dreamstime.com/b/big-grilled-chicken-burger-double-cutlet-cheese-wooden-background-side-view-close-up-208658240.jpg")
            )
            produtosList.add(Produtos(2,"Mega Burger",7500.00,"burguer",
                "https://thumbs.dreamstime.com/b/yummy-grilled-chicken-burger-cheese-bacon-black-background-side-view-close-up-234010525.jpg"))
            produtosList.add(Produtos(3,"Twin Burger",3500.00,"burguer",
                "https://thumbs.dreamstime.com/b/yummy-grilled-chicken-burger-double-cutlet-cola-wooden-table-side-view-hamburger-fast-food-concept-220539075.jpg"))
            produtosList.add(Produtos(4,"Crazy B",5000.00,"burguer",
                "https://media.istockphoto.com/photos/big-burger-on-wooden-table-picture-id1079827462?k=20&m=1079827462&s=612x612&w=0&h=U4rstc7Vm1iYHRaQPpBh23FZOuUxxk0ER-9g90Max1s="))
            produtosList.add(Produtos(5,"Another Burger",2000.00,"burguer",
                "https://media.istockphoto.com/photos/sandwich-with-chicken-burger-tomatoes-cheese-and-lettuce-picture-id478486992?k=20&m=478486992&s=612x612&w=0&h=PwW7y2ZYAPeXzS0S4YFyqLonv5zLYA5XsjPgrNFEYyE="))
            produtosList.add(Produtos(6,"Burgerzia",7000.00,"burguer",
                "https://images.squarespace-cdn.com/content/v1/5a78331264b05f6e4e46c86c/1554916281848-8A4ELKR8NU15C3HKXQX2/smashburger18.jpg"))
            produtosList.add(Produtos(7,"Family Pack",8000.00,"burguer",
                "https://staticfanpage.akamaized.net/wp-content/uploads/sites/22/2021/06/iStock-1248291191-1200x675.jpg"))
            produtosList.add(Produtos(8,"Mata Fome!",6400.00,"burguer",
                "https://topwinnipeg.com/wp-content/uploads/2022/01/burger-with-cheese-2021-08-26-17-52-23-utc-1024x713.jpg"))

            //==================================REFRIGERANTES==========================================
            produtosList.add(Produtos(9,"Fanta",500.00,"refrigerante",
                    "https://cdn.shopify.com/s/files/1/0611/2985/1133/products/fanta-orange-12oz-355ml-800x800_1024x1024_2x_73ffdf92-272c-4cca-9843-f0134a6ad140_1200x1200.webp?v=1649320854")
            )
            produtosList.add(Produtos(10,"Coca-Cola",600.00,"refrigerante",
                "https://www.drogariaminasbrasil.com.br/media/catalog/product/r/e/refrigerante_coca_cola_lata_350ml.jpg"))
            produtosList.add(Produtos(11,"Sprite",300.00,"refrigerante",
                "https://m2n.nyc3.cdn.digitaloceanspaces.com/catalogo/7894900681024.webp"))
            produtosList.add(Produtos(12,"Blue",100.00,"refrigerante",
                "https://www.alimentaangola.co.ao/wp-content/uploads/2021/08/REFRI-MARACUJA-LATA-BLUE-330ml.jpg"))
            produtosList.add(Produtos(13,"Pepsi",200.00,"refrigerante",
                "https://diariolibre.blob.core.windows.net.optimalcdn.com/images/binrepository/shutterstock-1481415659_17053662_20210817230135.jpg"))
            produtosList.add(Produtos(14,"7Up",700.00,"refrigerante",
                "https://aghacuisine.ca/wp-content/uploads/2022/01/7UP-e1642656690617.png"))
            produtosList.add(Produtos(15,"Mirinda",800.00,"refrigerante",
                "https://pontofresco.ao/wp-content/uploads/2021/01/b-54abb9b5a9c14fd6a3a71425f44b0361.jpeg"))
            produtosList.add(Produtos(16,"American Cola",640.00,"refrigerante",
                "https://www.monarchbeverages.com/sites/default/files/styles/max_1300x1300/public/media/pack_AC_v2.png?itok=C5u5KCAV"))

            //=============================PIZZAS=======================================================
            produtosList.add(Produtos(17,"Super Pizza",500.00,"pizza",
                "https://s.calendarr.com/upload/datas/pi/zz/pizza_c.jpg?auto_optimize=low&width=640")
            )
            produtosList.add(Produtos(18,"Sharing Pizza",600.00,"pizza",
                "https://media-cdn.tripadvisor.com/media/photo-s/18/1a/d5/1e/casteloes.jpg"))
            produtosList.add(Produtos(19,"Slicy",300.00,"pizza",
                "https://pizzariadesucesso.com/wp-content/uploads/2018/05/pepperoni-pizza.jpg"))
            produtosList.add(Produtos(20,"Special Pizza",100.00,"pizza",
                "https://blog.monouso.pt/wp-content/uploads/portada-pizza-redonda.jpg"))
            produtosList.add(Produtos(21,"Roman's Pizza",200.00,"pizza",
                "https://img.buzzfeed.com/thumbnailer-prod-us-east-1/video-api/assets/216054.jpg"))
            produtosList.add(Produtos(22,"Tablet Pizza",700.00,"pizza",
                "https://moinhoglobo.com.br/wp-content/uploads/2019/02/01-massa-pizza-1024x683.png"))
            produtosList.add(Produtos(23,"Sinmbute",800.00,"pizza",
                "https://res.cloudinary.com/swiggy/image/upload/fl_lossy,f_auto,q_auto,w_508,h_320,c_fill/lljrnmleo6htpnwtbhf7"))
            produtosList.add(Produtos(24,"Pepperoni",640.00,"pizza",
                "https://dcom-prod.imgix.net/files/wp-content/uploads/2016/06/1465586619-Pizzas-exoticas.jpg?w=1280&h=720&crop=focalpoint&fp-x=0.5&fp-y=0.1&fit=crop&auto=compress&q=75"))

            //=================================GELADOS==================================================
            produtosList.add(Produtos(25,"Mini Cream",140.00,"gelado",
                "https://img.grouponcdn.com/seocms/CHypTtbFzbcmkQ4sCKe1RvReVca/revised_0005_hero_jpg-600x390"))
            produtosList.add(Produtos(26,"Creamberry",240.00,"gelado",
                "https://historicvirginiatravel.com/wp-content/uploads/2022/03/ice-cream-sundae.jpg"))
            produtosList.add(Produtos(27,"Gelatto",340.00,"gelado",
                "https://www.discoverlosangeles.com/sites/default/files/media/Restaurants/fair-oaks-pharmacy-fair-oaks-sundae.jpg?width=1600&height=1200&fit=crop&quality=78&auto=webp"))
            produtosList.add(Produtos(28,"Popstickle",440.00,"gelado",
                "https://food.fnr.sndimg.com/content/dam/images/food/fullset/2022/02/9/0/KC3004_katie-lee-biegel-edible-cereal-treat-bowls-for-ice-cream-sundae-2_s4x3.jpg.rend.hgtvcom.616.462.suffix/1644510309314.jpeg"))

            //==============================CHURRASCO==================================================
            produtosList.add(Produtos(29,"Franguiteh",640.00,"churrasco",
                "https://918230.smushcdn.com/2283449/wp-content/uploads/2022/01/barbecue-1536x1025.jpg?lossy=1&strip=1&webp=1"))
            produtosList.add(Produtos(30,"Frango Frito",540.00,"churrasco",
                "https://media.socialdeal.nl/bedrijf/bbq-en-grill-hoorn-20011015361090.jpg"))
            produtosList.add(Produtos(31,"Arroz Frangal",840.00,"churrasco",
                "https://blog.supernosso.com/wp-content/uploads/2021/09/Costela-no-barbecue-opt-540x304.jpg"))
            produtosList.add(Produtos(32,"Frango Familiar",940.00,"churrasco",
                "https://moodysbutchershop.com/wp-content/uploads/2017/08/Half-Chicken.jpg"))
            produtosList.add(Produtos(34,"Frango Grelhado",140.00,"churrasco",
                "https://www.foodnetwork.com/content/dam/images/food/video/0/01/013/0137/0137773.jpg"))
            produtosList.add(Produtos(35,"Frango no Espeto",40.00,"churrasco",
                "https://www.afamilyfeast.com/wp-content/uploads/2020/06/How-to-Grill-Chicken-Drumsticks3.jpg"))

            //==============================CERVEJA==================================================
            produtosList.add(Produtos(36,"Bud Light",640.00,"cerveja",
                "https://www.beeruniversestore.com/wp-content/uploads/2020/12/brew_hero-image-scaled-e1620826370837.jpg"))
            produtosList.add(Produtos(37,"Heineken",540.00,"cerveja",
                "https://guiacidadesapp.com.br/wp-content/uploads/2021/10/heineken-reproducao.jpeg"))
            produtosList.add(Produtos(38,"Cuca",840.00,"cerveja",
                "https://mir-s3-cdn-cf.behance.net/projects/404/d7a484124700467.Y3JvcCw3NDc0LDU4NDYsMzg4LDA.jpg"))
            produtosList.add(Produtos(39,"Nocal",940.00,"cerveja",
                "https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/f7955a85294113.5d77abc2e00f0.jpg"))
            produtosList.add(Produtos(40,"Eka",140.00,"cerveja",
                "https://angola-online.net/upload/media/posts/2020-02/28/19c7f1595b4f038843a2f25b0eff0397_1582875828-b.jpg"))
            produtosList.add(Produtos(41,"Tigra",40.00,"cerveja",
                "https://mir-s3-cdn-cf.behance.net/project_modules/1400/d9e89441362171.57a2c2904fa43.jpg"))



            return produtosList
        }

        fun getALLBurgersList():ArrayList<Produtos>{
            var burgersList = ArrayList<Produtos>()

            for (item in 0 until getAllProdutos().size){
                var produto = getAllProdutos()[item]

                if (produto.tipo.equals("burguer"))
                    burgersList.add(produto)

            }

            return burgersList
        }

        fun getALLRefrigeranteList():ArrayList<Produtos>{
            var refrigeranteList = ArrayList<Produtos>()

            for (item in 0 until getAllProdutos().size){
                var produto = getAllProdutos()[item]

                if (produto.tipo.equals("refrigerante"))
                    refrigeranteList.add(produto)

            }

            return refrigeranteList
        }

        fun getALLPizzaList():ArrayList<Produtos>{
            var pizzaList = ArrayList<Produtos>()

            for (item in 0 until getAllProdutos().size){
                var produto = getAllProdutos()[item]

                if (produto.tipo.equals("pizza"))
                    pizzaList.add(produto)

            }

            return pizzaList
        }

        fun getALLChurrascoList():ArrayList<Produtos>{
            var churrascoList = ArrayList<Produtos>()

            for (item in 0 until getAllProdutos().size){
                var produto = getAllProdutos()[item]

                if (produto.tipo.equals("churrasco"))
                    churrascoList.add(produto)

            }

            return churrascoList
        }

        fun getALLGeladoList():ArrayList<Produtos>{
            var geladoList = ArrayList<Produtos>()

            for (item in 0 until getAllProdutos().size){
                var produto = getAllProdutos()[item]

                if (produto.tipo.equals("gelado"))
                    geladoList.add(produto)

            }

            return geladoList
        }

        fun getALLCervejaList():ArrayList<Produtos>{
            val cervejaList = ArrayList<Produtos>()

            for (item in 0 until getAllProdutos().size){
                val produto = getAllProdutos()[item]

                if (produto.tipo.equals("cerveja"))
                    cervejaList.add(produto)

            }

            return cervejaList
        }

        //===============================ALL CARTLIST=================================================
        //============================================================================================

        fun loadCartItems():ArrayList<CartItem> {
            val cartItemList = ArrayList<CartItem>()
            val produtosList = ArrayList<Produtos>()

            //===============================HAMBURGERS=================================================
            produtosList.add(
                Produtos(1,"Mad Burger",4500.00,"burguer",
                    "https://thumbs.dreamstime.com/b/big-grilled-chicken-burger-double-cutlet-cheese-wooden-background-side-view-close-up-208658240.jpg")
            )


            produtosList.add(
                Produtos(10,"Coca-Cola",600.00,"refrigerante",
                    "https://www.drogariaminasbrasil.com.br/media/catalog/product/r/e/refrigerante_coca_cola_lata_350ml.jpg")
            )


            produtosList.add(
                Produtos(19,"Slicy",300.00,"pizza",
                    "https://pizzariadesucesso.com/wp-content/uploads/2018/05/pepperoni-pizza.jpg")
            )


            produtosList.add(
                Produtos(26,"Creamberry",240.00,"gelado",
                    "https://historicvirginiatravel.com/wp-content/uploads/2022/03/ice-cream-sundae.jpg")
            )


            produtosList.add(
                Produtos(30,"Frango Frito",540.00,"churrasco",
                    "https://media.socialdeal.nl/bedrijf/bbq-en-grill-hoorn-20011015361090.jpg")
            )


            produtosList.add(
                Produtos(36,"Bud Light",640.00,"cerveja",
                    "https://www.beeruniversestore.com/wp-content/uploads/2020/12/brew_hero-image-scaled-e1620826370837.jpg")
            )



            for (produto in produtosList){
               cartItemList.add(CartItem(produto,MetodosUsados.getRandomNumbers(1,10)))
            }
            return cartItemList

        }

    }
}
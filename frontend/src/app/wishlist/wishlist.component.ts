import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { WishlistService } from '../_services/wishlist.service';
import { CartService } from '../_services/cart.service';


@Component({
  selector: 'app-wishlist',
  templateUrl: './wishlist.component.html',
  styleUrls: ['./wishlist.component.css']
})
export class WishlistComponent implements OnInit {

  wishlistData: any = [];
  public cartList: any = [];
  public totalItem: number = 0;
  constructor(private http: HttpClient,
    private wishlistservice: WishlistService,
    private CartService: CartService,
    ) { }

  ngOnInit(): void {
    this.getWishlistProducts();
  }

  getWishlistProducts() {
    const userName = localStorage.getItem("userName");
    this.wishlistservice.getWishlist().subscribe(
      (resp) => {
        console.log(resp);
        this.wishlistData = resp;
        console.log(this.wishlistData);
      }
    );
  }

  deleteWishlistProduct(id: number) {
    this.wishlistservice.deleteWishlistProduct(id).subscribe(
      (resp) => {
        console.log(resp);
        this.getWishlistProducts();
      },
      (error) => console.log(error)
    );
  }

  addtocart(product: any) {
    this.getCount();
    this.CartService.addCartList(product);
    this.getCount();
    alert("Product Added to cart! Proceed to checkout");
  }
  public getCount() {
    this.CartService.getCartList().subscribe(res => {
      this.cartList = res
      this.totalItem = this.cartList.length;
    });
  }

}

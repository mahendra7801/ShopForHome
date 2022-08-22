import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CartService } from '../_services/cart.service';
import { DiscountService } from '../_services/discount.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  public product: any = [];
  public size: number = 0;
  public grandTotal: number = 0;
  public total!: number;
  public message!: string;
  public couponDiscount: any = [];
  constructor(private cartService: CartService,
    private discountService: DiscountService,
    private router: Router,) { }

  ngOnInit(): void {

    this.getCartItems();
    this.getCouponList();

  }

  getCartItems() {
    this.cartService.getCartList().subscribe(
      (resp) => {
        this.product = resp;
        console.log(this.product);
        for (let i = 0; i < this.product.length; i++) {
          console.log(this.product[i].cartCost);
          this.grandTotal = this.grandTotal + this.product[i].cartCost;
        }
      }, (error) => console.log(error)
    );
  }

  removeItem(item: any) {
    this.cartService.deleteCartList(item.cartId).subscribe(
      (resp) => {
        console.log(resp);
        this.getCartItems();
        window.location.reload();
      },
      (error) => console.log(error)
    );
  }

  emptycart() {
    this.cartService.deleteEverythingFromCart().subscribe(
      (resp) => {
        this.getCartItems();
      }, (error) => console.log(error)
    );
  }

  increaseQuantity(item: any) {
    this.cartService.increaseQuantity(item).subscribe(
      (res) => {
        console.log(res);
        this.getCartItems();
      }, (error) => console.log(error)
    );
    window.location.reload();
  }

  decreaseQuantity(item: any) {
    if (item.Quantity_number == 1) {
      alert("Quantity should be greater or equal to 1 cannot reduce Less than 1");
    }
    else {
      this.cartService.decreaseQuantity(item).subscribe(
        (res) => {
          console.log(res);
          this.getCartItems();
        }, (error) => console.log(error)
      );
      window.location.reload();
    }
  }

  getCouponList() {
    let userName = localStorage.getItem("userName")!;
    this.discountService.getUserCoupons().subscribe(
      (res) => {
        console.log(res);
        this.couponDiscount = res;
        for (let i = 0; i < this.couponDiscount.length; i++) {
          if (this.couponDiscount[i].user.userName === userName) {
            this.message = "You are Eligible for " + this.couponDiscount[i].discountAmount + " discount";
          }
        }
      }, (error) => console.log(error)
    );
  }

  checkout(product: any, grandTotal: number) {
    let products: any[] = [];
    console.log(grandTotal);
    //console.log(product[0].product);
    for (let i = 0; i < product.length; i++) {
      console.log(product[i].cartCost);
      this.cartService.addToOrders(product[i].product, grandTotal, product[i].cartCost, product[i].quantity_number).subscribe(
        (res) => {
          console.log(res);
          if(i == product.length - 1) {
            alert("Checkout Successful !");
          }
          
          this.router.navigate(['/orderPage']);
          this.emptycart();
        },
        (error) => console.log(error)
      );
    }
  }
}

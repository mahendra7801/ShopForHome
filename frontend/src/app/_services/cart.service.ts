import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Product } from '../_model/product.model';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  public product: any[] = [];
  public sumTotal: any = [];
  requestHeader = new HttpHeaders(
    {
      "No-Auth": "True"
    }
  );

  requestBody = new HttpHeaders({
    "No-Auth": "True",
    "Accept": "application/json",
    "Content-Type": "application/json"
  });

  constructor(private http: HttpClient) { }

  public getCartList() {
    var userName = localStorage.getItem("userName")!;

    return this.http.get("http://localhost:9090/cart/getCart/" + userName);

  }

  public addCartList(product: Product) {

    console.log(product);
    var userName: string = localStorage.getItem("userName")!;
    return this.http.post("http://localhost:9090/cart/addToCart/" + userName, product, { headers: this.requestHeader }).subscribe(resp => console.log(resp));

  }

  public deleteCartList(id: number) {

    return this.http.delete("http://localhost:9090/cart/removeCartProduct/" + id);

  }

  public deleteEverythingFromCart() {
    return this.http.delete("http://localhost:9090/cart/removeAllProducts");
  }

  increaseQuantity(item: any): Observable<Object> {

    console.log(item);
    return this.http.put("http://localhost:9090/cart/increaseQuantity/" + item.cartId, item);

  }
  decreaseQuantity(item: any): Observable<Object> {
    console.log(item);
    return this.http.put("http://localhost:9090/cart/decreaseQuantity/" + item.cartId, item);


  }

  public addToOrders(products: any, grandTotal: number, cartCost: number, quantity: number) {
    let userName = localStorage.getItem("userName");
    return this.http.post("http://localhost:9090/addToOrders/" + userName + "/" + grandTotal + "/" + cartCost + "/" + quantity, products);

  }
}

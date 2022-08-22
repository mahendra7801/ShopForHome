import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../_model/product.model';
import { Wishlist } from '../_model/wishlist.model';

@Injectable({
  providedIn: 'root'
})
export class WishlistService {
  LINK = "http://localhost:9090/wishlist";

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

  constructor(private httpClient: HttpClient) { }

  public getWishlist() {
    var userName: string = localStorage.getItem("userName")!;

    return this.httpClient.get(this.LINK + "/getWishlist/" + userName);

  }

  public addNewWishlist(product: Product) {
    console.log(product);
    var userName: string = localStorage.getItem("userName")!;
    var url = this.LINK + "/addToWishlist/" + userName;
    console.log(url);
    return this.httpClient.post("http://localhost:9090/wishlist/addToWishlist/" + userName, product, { headers: this.requestHeader }).subscribe(resp => console.log(resp));
  }

  public deleteWishlistProduct(id: number) {

    return this.httpClient.delete("http://localhost:9090/wishlist/removeWishlistProduct/" + id);
  }
}

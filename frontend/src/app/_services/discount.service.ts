import { HttpClient, HttpHeaders } from '@angular/common/http';
import { NONE_TYPE } from '@angular/compiler';
import { Injectable } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DiscountService {

  requestBody = new HttpHeaders({
    "No-Auth": "True",
    "Accept": "application/json",
    "Content-Type": "application/json"
  });
  constructor(private http: HttpClient) { }

  addCoupon(discountform: NgForm): Observable<Object> {

    return this.http.post("http://localhost:9092/addCoupon", discountform, { headers: this.requestBody });

  }

  getCoupons() {
    return this.http.get("http://localhost:9092/getCoupons");
  }

  deleteCoupon(id: number) {
    return this.http.delete("http://localhost:9092/deleteCoupon/" + id);
  }

  addUserCoupon(user: any, amnt: number) {

    return this.http.post("http://localhost:9090/addDiscountEligibility/" + user.userName + "/" + amnt, NONE_TYPE);
  }

  getUserCoupons() {
    return this.http.get("http://localhost:9090/getDiscountEligibility");
  }

  removeDiscount(id: number) {
    return this.http.delete("http://localhost:9090/deleteUserCoupon/" + id);
  }
}

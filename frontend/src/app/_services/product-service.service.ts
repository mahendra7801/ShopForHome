import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../_model/product.model';

@Injectable({
  providedIn: 'root'
})
export class ProductServiceService {

  requestHeader = new HttpHeaders(
    {
      "No-Auth": "True"
    }
  );
  constructor(private httpClient: HttpClient
  ) { }

  public addProduct(product: Product) {
    return this.httpClient.post<Product>("http://localhost:9090/addNewProduct", product);
  }

  public getProducts() {
    return this.httpClient.get("http://localhost:9090/getProducts");
  }

  getProductById(productId: number): Observable<Product> {
    return this.httpClient.get<Product>("http://localhost:9090/getProduct/" + productId, { headers: this.requestHeader });
  }

  updateProduct(productId: number, product: Product): Observable<Object> {
    return this.httpClient.put("http://localhost:9090/products/" + productId, product);
  }

  deleteProduct(productId: number): Observable<Object> {
    return this.httpClient.delete("http://localhost:9090/deleteProduct/" + productId);
  }
}

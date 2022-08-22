import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SalesReportService {

  constructor(private http: HttpClient) { }

  getSalesReport() {

    return this.http.get("http://localhost:9091/getSalesReport");
  }

}

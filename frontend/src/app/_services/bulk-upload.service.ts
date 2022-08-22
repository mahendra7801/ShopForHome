import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BulkUploadService {
  baseApiUrl = "http://localhost:9090/upload"
  constructor(private http: HttpClient) { }

  upload(file: File): Observable<any> {
    const formData = new FormData();
    formData.append("file", file, file?.name);
    return this.http.post(this.baseApiUrl, formData);
  }
}

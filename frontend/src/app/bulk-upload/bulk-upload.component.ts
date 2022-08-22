import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BulkUploadService } from '../_services/bulk-upload.service';

@Component({
  selector: 'app-bulk-upload',
  templateUrl: './bulk-upload.component.html',
  styleUrls: ['./bulk-upload.component.css']
})
export class BulkUploadComponent implements OnInit {

  shortLink: string = "";
  loading: boolean = false;
  file!: File;
  constructor(private bulkUpload: BulkUploadService
    , private router: Router) { }

  ngOnInit(): void {
  }

  onChange(event: any) {
    this.file = event.target.files[0];

  }

  onUpload() {
    this.loading = !this.loading;
    console.log(this.file);
    this.bulkUpload.upload(this.file).subscribe(
      (event: any) => {
        if (typeof (event) === 'object') {
          this.shortLink = event.link;
          this.loading = false;
          alert("File Uploaded Successfully!")
        }
      }, (error) => console.log(error)
    );

  }
}

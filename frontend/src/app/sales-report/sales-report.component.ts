import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ExcelService } from '../_services/excel.service';
import { SalesReportService } from '../_services/sales-report.service';

@Component({
  selector: 'app-sales-report',
  templateUrl: './sales-report.component.html',
  styleUrls: ['./sales-report.component.css']
})
export class SalesReportComponent implements OnInit {

  salesDetails: any = [];
  salesDate: any = [];
  constructor(private salesService: SalesReportService,
    private excelService: ExcelService) { }

  ngOnInit(): void {
    this.getSalesReport();
  }

  getSalesReport() {

    this.salesService.getSalesReport().subscribe(
      (res) => {
        console.log(res);
        this.salesDetails = res;
      },
      (error) => console.log(error)
    );
  }

  
  getReport() {
    this.salesService.getSalesReport().subscribe(response => this.excelService.exportAsExcelFile(response,"Sales Report"));
    
  }

  date(dateForm: NgForm) {
    console.log()
    let sample: any = [];
    console.log(dateForm.value.startDate);
    this.salesService.getSalesReport().subscribe(
      (res) => {
        sample = res;
        console.log(sample);
        for (let i = 0; i < sample.length; i++) {
          let date: Date = sample[i].orderDate;
          let startDate: Date = dateForm.value.startDate;
          let endDate: Date = dateForm.value.endDate;
          if (date < endDate && date > startDate) {
            this.salesDate = sample[i]
          }
          else {
            continue;
          }
        }
      }
    )
  }
}

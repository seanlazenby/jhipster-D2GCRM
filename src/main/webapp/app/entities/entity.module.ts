import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'region',
        loadChildren: './region/region.module#D2GcrmRegionModule'
      },
      {
        path: 'office',
        loadChildren: './office/office.module#D2GcrmOfficeModule'
      },
      {
        path: 'country',
        loadChildren: './country/country.module#D2GcrmCountryModule'
      },
      {
        path: 'lead',
        loadChildren: './lead/lead.module#D2GcrmLeadModule'
      },
      {
        path: 'hotel',
        loadChildren: './hotel/hotel.module#D2GcrmHotelModule'
      },
      {
        path: 'location',
        loadChildren: './location/location.module#D2GcrmLocationModule'
      },
      {
        path: 'department',
        loadChildren: './department/department.module#D2GcrmDepartmentModule'
      },
      {
        path: 'task',
        loadChildren: './task/task.module#D2GcrmTaskModule'
      },
      {
        path: 'employee',
        loadChildren: './employee/employee.module#D2GcrmEmployeeModule'
      },
      {
        path: 'job',
        loadChildren: './job/job.module#D2GcrmJobModule'
      },
      {
        path: 'job-history',
        loadChildren: './job-history/job-history.module#D2GcrmJobHistoryModule'
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ],
  declarations: [],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class D2GcrmEntityModule {}

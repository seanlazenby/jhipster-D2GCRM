import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { D2GcrmSharedModule } from 'app/shared';
import {
  DepartmentComponent,
  DepartmentDetailComponent,
  DepartmentUpdateComponent,
  DepartmentDeletePopupComponent,
  DepartmentDeleteDialogComponent,
  departmentRoute,
  departmentPopupRoute
} from './';

const ENTITY_STATES = [...departmentRoute, ...departmentPopupRoute];

@NgModule({
  imports: [D2GcrmSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    DepartmentComponent,
    DepartmentDetailComponent,
    DepartmentUpdateComponent,
    DepartmentDeleteDialogComponent,
    DepartmentDeletePopupComponent
  ],
  entryComponents: [DepartmentComponent, DepartmentUpdateComponent, DepartmentDeleteDialogComponent, DepartmentDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class D2GcrmDepartmentModule {}

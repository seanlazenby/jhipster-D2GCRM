import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IOffice, Office } from 'app/shared/model/office.model';
import { OfficeService } from './office.service';

@Component({
  selector: 'jhi-office-update',
  templateUrl: './office-update.component.html'
})
export class OfficeUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    officeCity: [],
    officeCountry: []
  });

  constructor(protected officeService: OfficeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ office }) => {
      this.updateForm(office);
    });
  }

  updateForm(office: IOffice) {
    this.editForm.patchValue({
      id: office.id,
      officeCity: office.officeCity,
      officeCountry: office.officeCountry
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const office = this.createFromForm();
    if (office.id !== undefined) {
      this.subscribeToSaveResponse(this.officeService.update(office));
    } else {
      this.subscribeToSaveResponse(this.officeService.create(office));
    }
  }

  private createFromForm(): IOffice {
    const entity = {
      ...new Office(),
      id: this.editForm.get(['id']).value,
      officeCity: this.editForm.get(['officeCity']).value,
      officeCountry: this.editForm.get(['officeCountry']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOffice>>) {
    result.subscribe((res: HttpResponse<IOffice>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
